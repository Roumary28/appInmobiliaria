const app = document.getElementById('typewriter');

const typewriter = new Typewriter(app, {
    loop: true,
    delay:200
});

typewriter
    .typeString('MrHouse')
    .pauseFor(10)
    .start();