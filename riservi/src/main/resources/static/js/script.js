
const openPopupBtn = document.getElementById('openPopupBtn');
const closePopupBtn = document.getElementById('closePopupBtn');
const popup = document.getElementById('popup');


openPopupBtn.addEventListener('click', () => {
    popup.style.display = 'flex';
});


closePopupBtn.addEventListener('click', () => {
    popup.style.display = 'none';
});

window.addEventListener('click', (event) => {
    if (event.target === popup) {
        popup.style.display = 'none';
    }
});

function showModal(message) {
  document.getElementById("modalMessage").innerText = message;
  document.getElementById("successModal").style.display = "block";
}

function closeModal() {
  document.getElementById("successModal").style.display = "none";
}

 function submitForm() {
        const formData = new FormData(document.getElementById("reservationForm"));

        const data = {
            name: formData.get("name"),
            lastName: formData.get("lastname"),
            email: formData.get("email"),
            numberPhone: formData.get("phone"),
            date: formData.get("date"),
            hour: formData.get("hour")
        };

        fetch("http://localhost:8080/riservi/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(data => {
            console.log("Result:", data);
            showModal(data.message);

            const formData = new FormData(document.getElementById("reservationForm"));
            popup.style.display = 'none';
            location.reload();
        })
        .catch(error => {
            console.error("Error al realizar la reserva:", error);
            showModal(data.message);
        });
    }

    function getSchedule() {
            const formData = new FormData(document.getElementById("reservationForm"));

            const data = {
                name: formData.get("name"),
                lastName: formData.get("lastname"),
                email: formData.get("email"),
                numberPhone: formData.get("phone"),
                date: formData.get("date"),
                hour: formData.get("hour")
            };

            fetch("http://localhost:8080/riservi/add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
            .then(response => response.json())
            .then(data => {
                console.log("Reserva realizada:", data.message);
                showModal(data.message);
            })
            .catch(error => {
                console.error("Error al realizar la reserva:", error);
                showModal(data.message);
            });
        }


function cargarHoras(dateElementId, hourElementId) {
    const date = document.getElementById(dateElementId).value;
    const url = `http://localhost:8080/riservi/schedule/listByDay?date=${date}`;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            const selectElement = document.getElementById(hourElementId);
            selectElement.innerHTML = '';

            const defaultOption = document.createElement('option');
            defaultOption.value = '';
            defaultOption.textContent = 'Selecciona hora';
            selectElement.appendChild(defaultOption);

            const hours = data.map(item => item.hour);
            if (hours.length > 0) {
                hours.forEach(hour => {
                    const option = document.createElement('option');
                    option.value = hour;
                    option.textContent = hour;
                    selectElement.appendChild(option);
                });
            } else {
                const option = document.createElement('option');
                option.value = '';
                option.textContent = 'No hay horario disponible';
                selectElement.appendChild(option);
            }
        })
        .catch(error => {
            console.error('Error al cargar las horas:', error);
            alert('Hubo un error al cargar los horarios disponibles.');
        });
}

document.getElementById('date').addEventListener('change', function() {
    cargarHoras('date', 'hour');
});

document.getElementById('fechaReserva').addEventListener('change', function() {
    cargarHoras('fechaReserva', 'newtime');
});




