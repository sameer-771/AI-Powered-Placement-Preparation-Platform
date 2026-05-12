(function authPage() {
  const loginForm = document.querySelector("[data-login-form]");
  const registerForm = document.querySelector("[data-register-form]");
  const forgotBtn = document.querySelector("[data-forgot-btn]");

  if (loginForm) {
    loginForm.addEventListener("submit", async (event) => {
      event.preventDefault();
      const payload = {
        email: loginForm.email.value,
        password: loginForm.password.value,
      };
      try {
        await AIPP.api.login(payload);
        AIPP.ui.showToast("Welcome back", "success");
        window.location.href = "dashboard.html";
      } catch (error) {
        AIPP.ui.showToast(error.message || "Login failed", "error");
      }
    });
  }

  if (registerForm) {
    registerForm.addEventListener("submit", async (event) => {
      event.preventDefault();
      const payload = {
        fullName: registerForm.fullName.value,
        email: registerForm.email.value,
        password: registerForm.password.value,
      };
      try {
        await AIPP.api.register(payload);
        AIPP.ui.showToast("Account created", "success");
        window.location.href = "dashboard.html";
      } catch (error) {
        AIPP.ui.showToast(error.message || "Registration failed", "error");
      }
    });
  }

  if (forgotBtn) {
    forgotBtn.addEventListener("click", () => {
      AIPP.ui.showToast("Password reset link will be sent if the account exists", "info");
    });
  }
})();
