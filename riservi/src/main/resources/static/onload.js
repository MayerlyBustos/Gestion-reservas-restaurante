

let reservas = [];
const reservasPorPagina = 5;
let paginaActual = 1;

async function cargarReservas() {
    try {
        const response = await fetch('http://localhost:8080/riservi/list');
        reservas = await response.json();

        mostrarReservas(paginaActual);

        mostrarPaginacion();
    } catch (error) {
        console.error("Error al cargar las reservas:", error);
        alert("Hubo un problema al cargar las reservas.");
    }
}

function mostrarReservas(pagina) {
    const inicio = (pagina - 1) * reservasPorPagina;
    const fin = pagina * reservasPorPagina;
    const reservasPagina = reservas.slice(inicio, fin);

    const tablaHtml = `
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID Reserva</th>
                    <th>Cliente</th>
                    <th>Fecha Reserva</th>
                    <th>Hora</th>
                    <th>Email</th>
                    <th>Teléfono</th>
                    <th>Fecha de Reserva</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                ${reservasPagina.map(reserva => `
                    <tr>
                        <td>${reserva.reservationId}</td>
                        <td>${reserva.customer.name} ${reserva.customer.lastName}</td>
                        <td>${reserva.schedule.date}</td>
                        <td>${reserva.schedule.hour}</td>
                        <td>${reserva.customer.email}</td>
                        <td>${reserva.customer.numberPhone}</td>
                        <td>${reserva.reservationDate}</td>
                        <td>
                            <button class="btn btn-edit" onclick="editarReserva(${reserva.reservationId})">Editar</button>
                            <button class="btn btn-delete" onclick="eliminarReserva(${reserva.reservationId})">Eliminar</button>
                        </td>
                    </tr>
                `).join('')}
            </tbody>
        </table>
    `;

    document.getElementById('tablaReservas').innerHTML = tablaHtml;
}



function mostrarPaginacion(paginaActual, totalPaginas) {
    let paginacionHtml = '<nav><ul class="pagination">';

    // Botón "Anterior"
    if (paginaActual > 1) {
        paginacionHtml += `
            <li class="page-item">
                <button class="page-link" onclick="cargarReservas(${paginaActual - 1})">Anterior</button>
            </li>
        `;
    }

    for (let i = 1; i <= totalPaginas; i++) {
        paginacionHtml += `
            <li class="page-item ${paginaActual === i ? 'active' : ''}">
                <button class="page-link" onclick="cargarReservas(${i})">${i}</button>
            </li>
        `;
    }

    if (paginaActual < totalPaginas) {
        paginacionHtml += `
            <li class="page-item">
                <button class="page-link" onclick="cargarReservas(${paginaActual + 1})">Siguiente</button>
            </li>
        `;
    }

    paginacionHtml += '</ul></nav>';
    document.getElementById('tablaReservas').innerHTML += paginacionHtml;
}



function editarReserva(id) {
    const reserva = reservas.find(r => r.reservationId === id);
    alert(`Editar reserva con ID: ${id} (Nombre: ${reserva.customer.name})`);
}

function eliminarReserva(id) {
    const confirmacion = confirm("¿Estás seguro de que deseas eliminar esta reserva?");
    if (confirmacion) {
        reservas = reservas.filter(reserva => reserva.reservationId !== id);

        mostrarReservas(paginaActual);
        mostrarPaginacion();
    }
}


function editarReserva(id) {
    const reserva = reservas.find(r => r.reservationId === id);

    document.getElementById('nombreCliente').value = reserva.customer.name;
    document.getElementById('fechaReserva').value = reserva.schedule.date;

    document.getElementById('editarReservaModal').style.display = 'block';

}

function cerrarModal() {
    document.getElementById('editarReservaModal').style.display = 'none';
}

cargarReservas();