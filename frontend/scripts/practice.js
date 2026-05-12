(function practicePage() {
  const listEl = document.querySelector("[data-question-list]");
  const filters = document.querySelector("[data-filter-form]");
  const submissionForm = document.querySelector("[data-submission-form]");

  if (!listEl) return;

  async function loadQuestions(params = "") {
    listEl.innerHTML = "<div class='skeleton' style='height: 40px'></div>";
    try {
      const data = await AIPP.api.listQuestions(params);
      const items = data.content || data;
      listEl.innerHTML = "";
      items.forEach((question) => {
        const card = document.createElement("div");
        card.className = "panel";
        card.innerHTML = `
          <strong>${question.title}</strong>
          <p>${question.description}</p>
          <div class='ribbon'>
            <span>${question.difficulty}</span>
            <span>${question.topic}</span>
          </div>
        `;
        listEl.appendChild(card);
      });
    } catch (error) {
      listEl.innerHTML = "<p>Unable to load questions.</p>";
    }
  }

  loadQuestions();

  if (filters) {
    filters.addEventListener("submit", (event) => {
      event.preventDefault();
      const difficulty = filters.difficulty.value;
      const search = filters.search.value;
      const params = new URLSearchParams();
      if (difficulty) params.append("difficulty", difficulty);
      if (search) params.append("search", search);
      loadQuestions(`?${params.toString()}`);
    });
  }

  if (submissionForm) {
    submissionForm.addEventListener("submit", async (event) => {
      event.preventDefault();
      const payload = {
        questionId: submissionForm.questionId.value,
        language: submissionForm.language.value,
        codeText: submissionForm.codeText.value,
        timeTakenSeconds: parseInt(submissionForm.timeTakenSeconds.value, 10) || 0,
      };
      try {
        await AIPP.api.createSubmission(payload);
        AIPP.ui.showToast("Submission captured", "success");
      } catch (error) {
        AIPP.ui.showToast(error.message || "Submission failed", "error");
      }
    });
  }
})();
