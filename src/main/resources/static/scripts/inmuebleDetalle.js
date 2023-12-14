function mostrarImagen(imagenClickeada) {
  // Obtener la ruta de la imagen clickeada
  var rutaImagen = imagenClickeada.src;

  // Actualizar la imagen en la fila-1
  document.getElementById("imagenGrande").src = rutaImagen;
}

const btn = document.getElementById('button');

document.getElementById('form')
 .addEventListener('submit', function(event) {
   event.preventDefault();

   btn.value = 'Enviando...';

   const serviceID = 'MrHouseOutlook';
   const templateID = 'MrHouseTemplate1';

   emailjs.sendForm(serviceID, templateID, this)
    .then(() => {
      btn.value = 'Enviar Consulta';
      alert('Email enviado!');
    }, (err) => {
      btn.value = 'Enviar Consulta';
      alert(JSON.stringify(err));
    });
});