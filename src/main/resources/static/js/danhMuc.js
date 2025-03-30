// JavaScript giữ nguyên nhưng thêm vào cuối file
document.addEventListener('DOMContentLoaded', function() {
    // Xử lý dropdown
    document.querySelectorAll('.sidebar .dropdown-toggle').forEach(toggle => {
        toggle.addEventListener('click', function(e) {
            e.preventDefault();
            const dropdown = this.closest('.dropdown');
            dropdown.classList.toggle('active');
            
            // Đóng các dropdown khác
            document.querySelectorAll('.sidebar .dropdown').forEach(other => {
                if (other !== dropdown) other.classList.remove('active');
            });
        });
    });

    // Đóng dropdown khi click ra ngoài
    document.addEventListener('click', function(e) {
        if (!e.target.closest('.sidebar .dropdown')) {
            document.querySelectorAll('.sidebar .dropdown').forEach(dropdown => {
                dropdown.classList.remove('active');
            });
        }
    });
});