
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
            console.log("Reserva realizada:", data.message);
            showModal(data.message);
        })
        .catch(error => {
            console.error("Error al realizar la reserva:", error);
            showModal(data.message);
        });
    }
