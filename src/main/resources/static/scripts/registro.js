document.addEventListener("DOMContentLoaded", function () {
  var tipoUsuario = document.querySelector(".selectTipoUsuario");
  var dropdownUsuario = document.querySelector(".dropdown-content");
  var tipoUsuarioTexto = document.getElementById("tipoUsuarioTexto");

  tipoUsuario.addEventListener("click", function () {
    // Alterna el estilo de display entre "none" y "block"
    dropdownUsuario.style.display =
      dropdownUsuario.style.display === "none" ? "block" : "none";
  });

  // Agrega un evento de clic a los elementos li dentro del dropdown
  dropdownUsuario.addEventListener("click", function (event) {
    // Verifica si el clic ocurrió en un elemento li
    if (event.target.tagName === "LI") {
      // Actualiza el texto del .selectTipoUsuario con el texto del li seleccionado
      tipoUsuarioTexto.textContent = event.target.textContent;
    }
  });

  var tipoCondicionFiscal = document.querySelector(
    ".selectTipoCondicionFiscal"
  );
  var dropdownCondicionFiscal = document.querySelector(".dropdown-content-cf");
  var tipoCondicionFiscalTexto = document.getElementById("tipoCondicionFiscalTexto");

  tipoCondicionFiscal.addEventListener("click", function () {
    // Alterna el estilo de display entre "none" y "block"
    dropdownCondicionFiscal.style.display =
      dropdownCondicionFiscal.style.display === "none" ? "block" : "none";
  });

  // Agrega un evento de clic a los elementos li dentro del dropdown
  dropdownCondicionFiscal.addEventListener("click", function (event) {
    // Verifica si el clic ocurrio en un elemento li
    if (event.target.tagName === "LI") {
      // Actualiza el texto del .selectTipoCondicionFiscal con el texto del li seleccionado
      tipoCondicionFiscalTexto.textContent = event.target.textContent;
    }
  });
});
