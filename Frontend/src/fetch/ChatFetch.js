const baseUrl = "http://localhost:8080/api";

const chatRequest = async (prompt) => {
    const response = await fetch(baseUrl + "/userStory", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": localStorage.getItem("apikey")
        },
        body: JSON.stringify({"prompt": prompt})
    });

    if (!response.ok) {
        throw new Error("Network response was not ok");
    }

    const data = await response.json();
    return data;
};

export default chatRequest;


