// Selectors
const loadTrainersBtn = document.getElementById("loadTrainersBtn");
const trainerList = document.getElementById("trainerList");

const createTrainerForm = document.getElementById("createTrainerForm");
const createTrainerMessage = document.getElementById("createTrainerMessage");

const updateTrainerForm = document.getElementById("updateTrainerForm");
const updateTrainerMessage = document.getElementById("updateTrainerMessage");

const deleteTrainerForm = document.getElementById("deleteTrainerForm");
const deleteTrainerMessage = document.getElementById("deleteTrainerMessage");

// call Get api to fetch all trainers  data as JSON
loadTrainersBtn.addEventListener("click", async function () {
    trainerList.innerHTML = "<p>Loading trainers...</p>";

    try {
        // invoke api
        const response = await fetch("http://localhost:8080/api/trainers");

        console.log("Response status:", response.status);
        console.log("Response ok:", response.ok);

        if (!response.ok) {
            const errorText = await response.text();
            console.error("Server response:", errorText);
            throw new Error("HTTP error: " + response.status);
        }

        // fetch the data and transform into jason
        const data = await response.json();
        console.log("Trainer data:", data);

        // output the data with helper method
        displayTrainers(data);
    } catch (error) {
        console.error("Error loading trainers:", error);
        trainerList.innerHTML = `
            <p>Failed to load trainers.</p>
            <p>Open DevTools Console to see the exact error.</p>
        `;
    }
});
// Display all trainers method as a helper
function displayTrainers(trainers) {
    trainerList.innerHTML = "";

    if (!Array.isArray(trainers) || trainers.length === 0) {
        trainerList.innerHTML = "<p>No trainers found.</p>";
        return;
    }

    trainers.forEach(trainer => {
        const trainerCard = document.createElement("div");
        trainerCard.className = "trainer-card";

        trainerCard.innerHTML = `
            <h3>${trainer.name ?? "No name"}</h3>
            <p>ID: ${trainer.id ?? "No id"}</p>
        `;

        trainerList.appendChild(trainerCard);
    });
}



// Test Post API: create one trainer

createTrainerForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const newTrainer = {
        name: document.getElementById("trainerName").value
    };

    createTrainerMessage.textContent = "Creating trainer...";

    try {
        const response = await fetch("http://localhost:8080/api/trainers", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newTrainer)
        });

        console.log("FORM POST Response status:", response.status);
        console.log("FORM POST Response ok:", response.ok);

        if (!response.ok) {
            const errorText = await response.text();
            console.error("FORM POST Server response:", errorText);
            throw new Error("FORM POST HTTP error: " + response.status);
        }

        const createdTrainer = await response.json();
        console.log("FORM Created trainer:", createdTrainer);

        createTrainerMessage.textContent = "Trainer created successfully!";
        createTrainerForm.reset();

        loadTrainersBtn.click();

    } catch (error) {
        console.error("Error creating trainer from form:", error);
        createTrainerMessage.textContent = "Failed to create trainer. Open Console.";
    }
});
async function createTestTrainer() {
    const newTrainer = {
        name: "Test Trainer"

    };

    try {

        const response = await fetch("http://localhost:8080/api/trainers", {

            method: "POST",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify(newTrainer)

        });

        console.log("POST Response status:", response.status);

        console.log("POST Response ok:", response.ok);

        if (!response.ok) {

            const errorText = await response.text();

            console.error("POST Server response:", errorText);

            throw new Error("POST HTTP error: " + response.status);

        }

        const createdTrainer = await response.json();

        console.log("Created trainer:", createdTrainer);

        alert("Trainer created successfully!");

        loadTrainersBtn.click();

    } catch (error) {

        console.error("Error creating trainer:", error);

        alert("Failed to create trainer. Open DevTools Console to see the exact error.");

    }

}

updateTrainerForm.addEventListener("submit", async function (event) {
    event.preventDefault();
    const trainerId = document.getElementById("updateTrainerId").value;


    const updateRequest = {
        name: document.getElementById("updateTrainerName").value
    };

    updateTrainerMessage.textContent = "Updating trainer...";

    try {
        const response = await fetch(`http://localhost:8080/api/trainers/${trainerId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(updateRequest)
        });

        console.log("PUT Response status:", response.status);
        console.log("PUT Response ok:", response.ok);

        if (!response.ok) {
            const errorText = await response.text();
            console.error("PUT Server response:", errorText);
            throw new Error("PUT HTTP error: " + response.status);
        }

        const updatedTrainer = await response.json();
        console.log("Updated trainer:", updatedTrainer);

        updateTrainerMessage.textContent = "Trainer updated successfully!";
        updateTrainerForm.reset();

        loadTrainersBtn.click();

    } catch (error) {
        console.error("Error updating trainer from form:", error);
        updateTrainerMessage.textContent = "Failed to update trainer. Open Console.";
    }
});





async function updateTestTrainer(id, newName) {
    const updateRequest = {
        name: newName
    };

    try {
        const response = await fetch(`http://localhost:8080/api/trainers/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(updateRequest)
        });

        console.log("PUT Response status:", response.status);
        console.log("PUT Response ok:", response.ok);

        if (!response.ok) {
            const errorText = await response.text();
            console.error("PUT Server response:", errorText);
            throw new Error("PUT HTTP error: " + response.status);
        }

        const updatedTrainer = await response.json();
        console.log("Updated trainer:", updatedTrainer);

        alert("Trainer updated successfully!");
        loadTrainersBtn.click();

    } catch (error) {
        console.error("Error updating trainer:", error);
        alert("Failed to update trainer. Open Console.");
    }
}

deleteTrainerForm.addEventListener("submit", async function (event) {
    event.preventDefault();

    const trainerId = document.getElementById("deleteTrainerId").value;

    deleteTrainerMessage.textContent = "Deleting trainer...";

    try {
        const response = await fetch(`http://localhost:8080/api/trainers/${trainerId}`, {
            method: "DELETE"
        });

        console.log("DELETE Response status:", response.status);
        console.log("DELETE Response ok:", response.ok);

        if (!response.ok) {
            const errorText = await response.text();
            console.error("DELETE Server response:", errorText);
            throw new Error("DELETE HTTP error: " + response.status);
        }

        deleteTrainerMessage.textContent = "Trainer deleted successfully!";
        deleteTrainerForm.reset();

        loadTrainersBtn.click();

    } catch (error) {
        console.error("Error deleting trainer from form:", error);
        deleteTrainerMessage.textContent = "Failed to delete trainer. Open Console.";
    }
});

async function deleteTestTrainer(id) {
    try {
        const response = await fetch(`http://localhost:8080/api/trainers/${id}`, {
            method: "DELETE"
        });

        console.log("DELETE Response status:", response.status);
        console.log("DELETE Response ok:", response.ok);

        if (!response.ok) {
            const errorText = await response.text();
            console.error("DELETE Server response:", errorText);
            throw new Error("DELETE HTTP error: " + response.status);
        }

        alert("Trainer deleted successfully!");
        loadTrainersBtn.click();

    } catch (error) {
        console.error("Error deleting trainer:", error);
        alert("Failed to delete trainer. Open Console.");
    }
}

window.deleteTestTrainer = deleteTestTrainer;

window.updateTestTrainer = updateTestTrainer;

