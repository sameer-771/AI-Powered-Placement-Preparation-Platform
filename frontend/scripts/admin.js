(function adminPage() {
  const usersTable = document.querySelector("[data-users-table]");
  const activityList = document.querySelector("[data-admin-activity]");

  if (!usersTable) return;

  AIPP.api.adminUsers()
    .then((users) => {
      usersTable.innerHTML = "";
      users.forEach((user) => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${user.fullName}</td>
          <td>${user.email}</td>
          <td>${user.role}</td>
          <td>${user.active ? "Active" : "Paused"}</td>
        `;
        usersTable.appendChild(row);
      });
    })
    .catch(() => {
      usersTable.innerHTML = "<tr><td colspan='4'>Unable to load users</td></tr>";
    });

  if (activityList) {
    AIPP.api.adminActivity()
      .then((items) => {
        activityList.innerHTML = "";
        items.forEach((item) => {
          const li = document.createElement("li");
          li.textContent = `${item.actionType}: ${item.actionDetails}`;
          activityList.appendChild(li);
        });
      })
      .catch(() => {
        activityList.innerHTML = "<li>No activity yet</li>";
      });
  }
})();
