function mostrarImagen(imagenClickeada) {
  // Obtener la ruta de la imagen clickeada
  var rutaImagen = imagenClickeada.src;

  // Actualizar la imagen en la fila-1
  document.getElementById("imagenGrande").src = rutaImagen;
}
