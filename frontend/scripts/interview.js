(function interviewPage() {
  const form = document.querySelector("[data-interview-form]");
  const output = document.querySelector("[data-interview-output]");

  if (!form) return;

  form.addEventListener("submit", async (event) => {
    event.preventDefault();
    const payload = {
      interviewType: form.interviewType.value,
      focusArea: form.focusArea.value,
      notes: form.notes.value,
    };
    try {
      const data = await AIPP.api.interviewFeedback(payload);
      output.innerHTML = `
        <h3>Score: ${data.score}</h3>
        <p><strong>Confidence:</strong> ${data.confidenceRating}</p>
        <p><strong>Questions:</strong> ${data.questionsAsked}</p>
        <p>${data.aiFeedback}</p>
      `;
      AIPP.ui.showToast("Interview feedback ready", "success");
    } catch (error) {
      AIPP.ui.showToast(error.message || "Interview failed", "error");
    }
  });
})();
