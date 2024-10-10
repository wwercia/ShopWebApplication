
//sprawia ze klikniety guzik na pasku bocznym ma odpowiedni kolor

document.addEventListener('DOMContentLoaded', function() {
    var currentUrl = window.location.pathname;

    var forms = document.querySelectorAll('.sidebar form');

    forms.forEach(function(form) {
        if (form.getAttribute('action') === currentUrl.split('/').pop()) {
            form.classList.add('active');
        }
    });
});
