
## 🚀 Công Nghệ Sử Dụng (Tech Stack)

Dự án này sử dụng các thư viện và kỹ thuật phổ biến nhất hiện nay:

- **Ngôn ngữ**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) (Khai báo giao diện hiện đại).
- **Dependency Injection**: [Hilt (Dagger)](https://developer.android.com/training/dependency-injection/hilt-android) (Quản lý các thành phần phụ thuộc).
- **Local Database**: [Room](https://developer.android.com/training/data-storage/room) (Lưu trữ dữ liệu ngoại tuyến).
- **Network**: [Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/) (Xử lý API).
- **Architecture**: MVVM (Model-ViewModel-View) kết hợp với Clean Architecture hướng feature.
- **Asynchronous**: Coroutines & Flow.

---

## 📂 Cấu Trúc Project (Project Structure)

Project được tổ chức theo hướng **Package by Feature** để dễ dàng mở rộng và bảo trì:

```text
app/src/main/java/com/adamfakee/learnkotlin/
├── di/                     # Các DI Module toàn cục (Database, Network, DataStore)
├── feature/                # Chứa các tính năng chính của ứng dụng
│   ├── home/               # Màn hình chính
│   ├── local/              # Tính năng lưu trữ Todo (Sử dụng Room)
│   │   ├── data/           # Entity, Dao cho Local Database
│   │   ├── di/             # Module riêng cho tính năng Local
│   │   └── ...             # Screens & ViewModels
│   ├── network/            # Tính năng gọi API Social/Product
│   │   ├── data/           # API Services & Models
│   │   └── ...             # Screens & ViewModels
│   ├── onboarding/         # Màn hình giới thiệu ban đầu
│   └── main/               # Các thành phần UI chính của App
├── navigation/             # Quản lý điều hướng (NavHost, Routes)
├── ui/                     # Giao diện chung (Theme, Components, Color)
├── data/                   # (Tùy chọn) Chứa Repository hoặc các tác vụ data dùng chung
├── MainActivity.kt         # Entry point của ứng dụng
└── LearnKotlinApp.kt       # Application class (khởi tạo Hilt)
```

---

## ✨ Các Tính Năng Chính (Feature Modules)

1.  **Local Feature (Todo App)**:
    - Sử dụng **Room Database** để thực hiện các thao tác CRUD (Thêm, Sửa, Xóa, Xem) danh sách công việc.
    - Minh họa cách kết hợp ViewModel với Flow để cập nhật UI realtime từ Database.

2.  **Network Feature**:
    - Sử dụng **Retrofit** để gọi API từ internet.
    - Xử lý trạng thái Loading, Error và Success trên giao diện.
    - Có màn hình danh sách và màn hình chi tiết (Detail Screen).


