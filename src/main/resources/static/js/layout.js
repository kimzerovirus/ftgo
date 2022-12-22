const layout = {
    init: function(){
        this.headerScrollEvent();
    },
    headerScrollEvent: function(){
        //참고 https://developer.mozilla.org/en-US/docs/Web/API/Document/scroll_event
        const header = document.querySelector("header");
        let ticking = false;
        
        document.addEventListener("scroll", (event) => {
          if (!ticking) {
            window.requestAnimationFrame(() => {
              header.classList.toggle("sticky", window.scrollY > 0);
              ticking = false;
            });
        
            ticking = true;
          }
        });
    }
}

layout.init();