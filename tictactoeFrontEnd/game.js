document.getElementById("registrationForm").addEventListener("submit", function (event) {
    event.preventDefault(); 

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    
    fetch("http://localhost:8080/api/game/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                localStorage.setItem("username", username);
                document.getElementById("registrationDiv").style.display = "none";
                document.getElementById("gameBoard").style.display = "block";
            } else {
                alert("Registration failed. Try again.");
            }
        })
        .catch(error => console.error("Error:", error));
});

const cells = document.querySelectorAll(".cell");

let currentPlayer = "X";
const username = localStorage.getItem("username");

cells.forEach((cell) => {
    cell.addEventListener("click", (event) => {
        const row = event.target.dataset.row;
        const col = event.target.dataset.col;

        if (event.target.textContent !== "") {
            alert("Cell is already filled!");
            return;
        }

        event.target.textContent = currentPlayer;
        makeMove(row, col, currentPlayer, username);

        currentPlayer = currentPlayer === "X" ? "O" : "X";
    });
});

function makeMove(row, col, player, username) {
    fetch("http://localhost:8080/api/game/move", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ row, col, player, username })
    })
        .then((response) => response.json())
        .then((data) => {
            console.log("Move response:", data);
            updateGameBoard(data.board);

          
            if (data.winner) {
                alert(data.winner + " wins!");
                resetGame();
            } else if (data.draw) {
                alert("It's a draw!");
                resetGame();
            }
        })
        .catch((error) => console.error("Error during move:", error));
}

function updateGameBoard(board) {
    const cells = document.querySelectorAll(".cell");
    let index = 0;
    for (let row = 0; row < 3; row++) {
        for (let col = 0; col < 3; col++) {
            cells[index].textContent = board[row][col] || "";
            index++;
        }
    }
}

function resetGame() {
   
    const cells = document.querySelectorAll(".cell");
    cells.forEach(cell => cell.textContent = "");
}

