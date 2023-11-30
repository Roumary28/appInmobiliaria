const app = document.getElementById('typewriter');

const typewriter = new Typewriter(app, {
    loop: true,
    delay:95
});

typewriter
    .typeString('MrHouse')
    .pauseFor(200)
    .start();