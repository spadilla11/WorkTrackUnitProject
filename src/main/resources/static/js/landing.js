document.addEventListener('DOMContentLoaded', () => {
    if (typeof lucide !== 'undefined') {
        lucide.createIcons();
    }

    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('active');

            }
        });
    }, observerOptions);

    const revealElements = document.querySelectorAll('.reveal, .slide-in-right');
    revealElements.forEach((el) => {
        observer.observe(el);
    });
});