window.AIPP = window.AIPP || {};

(function initApp() {
  const page = document.querySelector(".page");
  if (page) {
    requestAnimationFrame(() => page.classList.add("is-ready"));
  }

  const themeToggle = document.querySelector("[data-theme-toggle]");
  const storedTheme = localStorage.getItem("aipp_theme");
  if (storedTheme === "light") {
    document.body.classList.add("theme-light");
  }

  if (themeToggle) {
    themeToggle.addEventListener("click", () => {
      document.body.classList.toggle("theme-light");
      localStorage.setItem("aipp_theme", document.body.classList.contains("theme-light") ? "light" : "dark");
    });
  }

  const commandBackdrop = document.querySelector(".cmdk-backdrop");
  const commandInput = document.querySelector(".cmdk input");

  function openCommandPalette() {
    if (!commandBackdrop) return;
    commandBackdrop.style.display = "flex";
    if (commandInput) commandInput.focus();
  }

  function closeCommandPalette() {
    if (!commandBackdrop) return;
    commandBackdrop.style.display = "none";
  }

  document.addEventListener("keydown", (event) => {
    if ((event.ctrlKey || event.metaKey) && event.key.toLowerCase() === "k") {
      event.preventDefault();
      openCommandPalette();
    }
    if (event.key === "Escape") {
      closeCommandPalette();
    }
  });

  if (commandBackdrop) {
    commandBackdrop.addEventListener("click", (event) => {
      if (event.target === commandBackdrop) {
        closeCommandPalette();
      }
    });
  }
})();

AIPP.ui = {
  showToast(message, tone = "info") {
    let stack = document.querySelector(".toast-stack");
    if (!stack) {
      stack = document.createElement("div");
      stack.className = "toast-stack";
      document.body.appendChild(stack);
    }
    const toast = document.createElement("div");
    toast.className = "toast";
    toast.textContent = message;
    if (tone === "success") toast.style.borderColor = "rgba(105, 240, 180, 0.6)";
    if (tone === "error") toast.style.borderColor = "rgba(255, 107, 107, 0.6)";
    stack.appendChild(toast);
    setTimeout(() => toast.remove(), 4200);
  },
};
