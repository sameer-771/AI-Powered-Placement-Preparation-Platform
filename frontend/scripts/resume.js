(function resumePage() {
  const analyzeForm = document.querySelector("[data-resume-form]");
  const output = document.querySelector("[data-resume-output]");
  const exportBtn = document.querySelector("[data-export-pdf]");

  if (analyzeForm) {
    analyzeForm.addEventListener("submit", async (event) => {
      event.preventDefault();
      const payload = {
        resumeText: analyzeForm.resumeText.value,
        resumeFilename: analyzeForm.resumeFilename.value || "resume.pdf",
      };
      try {
        const data = await AIPP.api.analyzeResume(payload);
        output.innerHTML = `
          <h3>ATS Score: ${data.atsScore}</h3>
          <p><strong>Detected:</strong> ${data.detectedSkills}</p>
          <p><strong>Missing:</strong> ${data.missingSkills}</p>
          <p><strong>Recommendations:</strong> ${data.recommendations}</p>
        `;
        AIPP.ui.showToast("Resume analyzed", "success");
      } catch (error) {
        AIPP.ui.showToast(error.message || "Analysis failed", "error");
      }
    });
  }

  if (exportBtn) {
    exportBtn.addEventListener("click", () => {
      window.print();
    });
  }
})();
