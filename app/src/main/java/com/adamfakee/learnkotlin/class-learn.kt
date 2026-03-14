package com.adamfakee.learnkotlin

// ====================================================================
// 1. DATA CLASS: Chỉ dùng để chứa dữ liệu (Tự động có toString, equals...)
// ====================================================================
data class Product(val id: String, val name: String, val price: Double)
data class Order(val orderId: String, val items: List<Product>)

// ====================================================================
// 2. SEALED CLASS: Kiểm soát 100% các trạng thái thanh toán có thể xảy ra
// ====================================================================
sealed class PaymentStatus {
    // Trạng thái thành công: Bắt buộc kèm theo mã giao dịch
    data class Success(val transactionId: String) : PaymentStatus()

    // Trạng thái thất bại: Bắt buộc kèm theo lời nhắn lỗi
    data class Error(val errorMessage: String) : PaymentStatus()

    // Trạng thái đang xử lý: Không cần kèm dữ liệu gì thêm (dùng object)
    object Processing : PaymentStatus()
}

// ====================================================================
// 3. INTERFACE: Bản hợp đồng "Thanh toán" (Tính Trừu tượng)
// Bất kỳ hình thức nào muốn thanh toán được đều phải ký hợp đồng này.
// ====================================================================
interface PaymentMethod {
    // Chỉ định nghĩa "phải có hàm pay", không quan tâm bên trong làm gì
    fun pay(amount: Double): PaymentStatus
}

// ====================================================================
// 4. OOP TRONG THỰC TẾ: Tính Kế thừa, Đa hình & Đóng gói
// ====================================================================

// Ví Momo ký hợp đồng PaymentMethod
class MomoWallet(private val phoneNumber: String) : PaymentMethod {
    // Tính Đóng gói: phoneNumber là private, bên ngoài không thể lấy số điện thoại này được.

    override fun pay(amount: Double): PaymentStatus {
        println("Đang kết nối đến ví Momo của số $phoneNumber...")
        // Giả lập logic call API...
        return if (amount > 0) PaymentStatus.Success("MOMO-${System.currentTimeMillis()}")
        else PaymentStatus.Error("Số tiền không hợp lệ")
    }
}

// Thẻ Tín dụng ký hợp đồng PaymentMethod
class CreditCard(private val cardNumber: String) : PaymentMethod {
    override fun pay(amount: Double): PaymentStatus {
        // Chỉ in ra 4 số cuối để bảo mật
        val last4Digits = cardNumber.takeLast(4)
        println("Đang xác thực thẻ tín dụng đuôi $last4Digits qua cổng Visa/Mastercard...")
        // Giả lập logic call API ngân hàng...
        return PaymentStatus.Success("VISA-${System.currentTimeMillis()}")
    }
}

// ====================================================================
// HÀM CHẠY CHÍNH (Vận hành hệ thống)
// ====================================================================
fun main() {
    // 1. Dùng Data Class để tạo giỏ hàng cực nhanh
    val laptop = Product("P01", "MacBook Pro M3", 2000.0)


    val laptop2 = Product("P01", "MacBook Pro M3", 2000.0)

    println("compare laptop:::  ${laptop == laptop2}")

    val mouse = Product("P02", "Logitech Master 3S", 100.0)
    val myOrder = Order("ORD-999", listOf(laptop, mouse))

    val totalAmount = myOrder.items.sumOf { it.price } // Tổng tiền: 2100.0

    // 2. Tính ĐA HÌNH (Polymorphism) tỏa sáng ở đây:
    // Khách hàng chọn Momo hay Thẻ tín dụng thì hệ thống vẫn chỉ nhìn nó dưới góc độ là một "PaymentMethod".
    // Bạn thử đổi MomoWallet thành CreditCard("4111222233334444"), code bên dưới vẫn không cần đổi một dòng nào!
    val selectedPaymentMethod: PaymentMethod = MomoWallet("0901234567")

    println("Đang xử lý đơn hàng ${myOrder.orderId}. Tổng tiền: $$totalAmount")

    // 3. Gọi hàm interface
    val result: PaymentStatus = selectedPaymentMethod.pay(totalAmount)

    // 4. Sức mạnh của Sealed Class kết hợp lệnh 'when'
    // Trình biên dịch bắt buộc chúng ta phải xử lý ĐỦ 3 trường hợp.
    // Nếu bạn xóa dòng "is PaymentStatus.Error" đi, code sẽ báo lỗi đỏ lòm và không cho chạy (an toàn tuyệt đối).
    when (result) {
        is PaymentStatus.Success -> {
            println("✅ Tuyệt vời! Thanh toán thành công. Mã GD của bạn là: ${result.transactionId}")
        }
        is PaymentStatus.Error -> {
            println("❌ Rất tiếc! Thanh toán thất bại. Lý do: ${result.errorMessage}")
        }
        PaymentStatus.Processing -> {
            println("⏳ Hệ thống đang xử lý, vui lòng không tắt ứng dụng...")
        }
    }
}