const layout = {
  init: function () {
    this.sidebarToggleEvent();
  },
  sidebarToggleEvent() {
    const sidebar = document.getElementById("sidebar");
    const allSidebarDivider = document.querySelectorAll("#sidebar .divider");
    const toggleSidebarBtn = document.getElementById("toggle-sidebar");

    toggleSidebarBtn.addEventListener("click", function () {
      sidebar.classList.toggle("hide");

      if (sidebar.classList.contains("hide")) {
        allSidebarDivider.forEach((item) => {
          item.textContent = "-";
        });
      } else {
        allSidebarDivider.forEach((item) => {
          item.textContent = item.dataset.text;
        });
      }
    });

    sidebar.addEventListener('mouseenter', function () {
        if(this.classList.contains('hide')) {
            allSidebarDivider.forEach(item=> {
                item.textContent = item.dataset.text;
            })
        }
    })

    sidebar.addEventListener('mouseleave', function () {
        if(this.classList.contains('hide')) {
            allSidebarDivider.forEach(item=> {
                item.textContent = '-'
            })
        }
    })
  },
};

layout.init();
