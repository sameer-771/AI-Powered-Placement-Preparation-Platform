window.AIPP = window.AIPP || {};

(function apiModule() {
  const API_BASE = "http://localhost:8080/api";

  function getToken() {
    return localStorage.getItem("aipp_token");
  }

  function setToken(token) {
    localStorage.setItem("aipp_token", token);
  }

  function clearToken() {
    localStorage.removeItem("aipp_token");
  }

  async function apiRequest(path, options = {}) {
    const headers = options.headers || {};
    const token = getToken();
    if (token) headers.Authorization = `Bearer ${token}`;
    if (!headers["Content-Type"] && options.body) {
      headers["Content-Type"] = "application/json";
    }
    const response = await fetch(`${API_BASE}${path}`, {
      ...options,
      headers,
    });
    if (!response.ok) {
      const error = await response.json().catch(() => ({}));
      throw new Error(error.message || "Request failed");
    }
    return response.json();
  }

  async function login(payload) {
    const data = await apiRequest("/auth/login", {
      method: "POST",
      body: JSON.stringify(payload),
    });
    setToken(data.accessToken);
    return data;
  }

  async function register(payload) {
    const data = await apiRequest("/auth/register", {
      method: "POST",
      body: JSON.stringify(payload),
    });
    setToken(data.accessToken);
    return data;
  }

  function profile() {
    return apiRequest("/auth/me");
  }

  function dashboardSummary() {
    return apiRequest("/dashboard/summary");
  }

  function listQuestions(params = "") {
    return apiRequest(`/questions${params}`);
  }

  function createSubmission(payload) {
    return apiRequest("/submissions", {
      method: "POST",
      body: JSON.stringify(payload),
    });
  }

  function submissionHistory(params = "") {
    return apiRequest(`/submissions${params}`);
  }

  function analyzeResume(payload) {
    return apiRequest("/resume/analyze", {
      method: "POST",
      body: JSON.stringify(payload),
    });
  }

  function resumeHistory() {
    return apiRequest("/resume/history");
  }

  function interviewFeedback(payload) {
    return apiRequest("/interviews/feedback", {
      method: "POST",
      body: JSON.stringify(payload),
    });
  }

  function interviewHistory() {
    return apiRequest("/interviews/history");
  }

  function adminUsers() {
    return apiRequest("/admin/users");
  }

  function adminActivity() {
    return apiRequest("/admin/activity");
  }

  AIPP.api = {
    login,
    register,
    profile,
    dashboardSummary,
    listQuestions,
    createSubmission,
    submissionHistory,
    analyzeResume,
    resumeHistory,
    interviewFeedback,
    interviewHistory,
    adminUsers,
    adminActivity,
    clearToken,
  };
})();
