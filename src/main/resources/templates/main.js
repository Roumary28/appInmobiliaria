const app = document.getElementById('typewriter');

const typewriter = new Typewriter(app, {
    loop: true,
    delay:75
});

typewriter
    .typeString('La tierra del sol y del buen vino')
    .pauseFor(200)
    .start();