(function dashboardPage() {
  const summaryEl = document.querySelector("[data-dashboard-summary]");
  const chartEl = document.getElementById("weeklyChart");
  const counters = document.querySelectorAll("[data-counter]");
  const leaderboardEl = document.querySelector("[data-leaderboard]");

  function animateCounters(values) {
    counters.forEach((counter) => {
      const key = counter.dataset.counter;
      const target = values[key] || 0;
      let current = 0;
      const step = Math.max(1, Math.floor(target / 40));
      const timer = setInterval(() => {
        current += step;
        if (current >= target) {
          current = target;
          clearInterval(timer);
        }
        counter.textContent = current;
      }, 30);
    });
  }

  if (!summaryEl) return;

  AIPP.api.dashboardSummary()
    .then((data) => {
      summaryEl.querySelector("[data-name]").textContent = data.greetingName;
      summaryEl.querySelector("[data-streak]").textContent = `${data.streakDays} day streak`;
      summaryEl.querySelector("[data-weekly]").textContent = `${data.weeklyProgress}/${data.weeklyGoal} this week`;

      animateCounters({
        solved: data.totalSolved,
        total: data.totalQuestions,
        streak: data.streakDays,
      });

      const activityList = summaryEl.querySelector("[data-activity]");
      activityList.innerHTML = "";
      data.recentActivities.forEach((item) => {
        const li = document.createElement("li");
        li.textContent = item;
        activityList.appendChild(li);
      });

      if (chartEl && window.Chart) {
        new Chart(chartEl, {
          type: "line",
          data: {
            labels: data.weeklyLabels,
            datasets: [
              {
                label: "Focus Minutes",
                data: data.weeklyActivity,
                borderColor: "#6ae4ff",
                backgroundColor: "rgba(106, 228, 255, 0.2)",
                tension: 0.4,
                fill: true,
              },
            ],
          },
          options: {
            plugins: {
              legend: { display: false },
            },
            scales: {
              x: { grid: { color: "rgba(255,255,255,0.04)" } },
              y: { grid: { color: "rgba(255,255,255,0.04)" } },
            },
          },
        });
      }
    })
    .catch((err) => {
      AIPP.ui.showToast(err.message || "Unable to load dashboard", "error");
    });

  if (leaderboardEl) {
    AIPP.api.leaderboard()
      .then((entries) => {
        leaderboardEl.innerHTML = "";
        entries.forEach((entry) => {
          const li = document.createElement("li");
          li.textContent = `${entry.fullName} - ${entry.totalScore} pts`;
          leaderboardEl.appendChild(li);
        });
      })
      .catch(() => {
        leaderboardEl.innerHTML = "<li>No leaderboard data</li>";
      });
  }
})();
