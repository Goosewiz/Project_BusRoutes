document.addEventListener("DOMContentLoaded", () => {
  const table = document.getElementById("table-active_order_items");
  const loadingSpinner = document.getElementById("loading");
  const alert = document.getElementById("alert");
  const sortOptions = document.querySelectorAll("[sortBy]");

  let items = [];
  const sorting = {};

  sortOptions.forEach((s) => {
    s.addEventListener("click", (evt) => {
      setSorting(evt.target.attributes["sortBy"].value);
    });
  });

  setSorting("number");
  loadRoutes();

  function loadRoutes() {
    setLoading(loadingSpinner, true);
    setAlert(alert);
    table.hidden = true;

    api
      .getRoutes()
      .then((result) => {
        items = result.items.map((i) => ({
          number: i.number,
          type: i.type,
          timeStart: i.timeStart,
          timeEnd: i.timeEnd,
        }));
        showSortedItems();
        table.hidden = false;
      })
      .catch((err) => {
        console.error("getRoutes failed", err);
        setAlert(alert, "Произошла ошибка при загрузке маршрутов");
      })
      .finally(() => setLoading(loadingSpinner, false));
  }

  function showSortedItems() {
    items = sorted(items, sorting);
    setRows(table, items);
  }

  function setSorting(field) {
    changeSorting(sorting, field);
    showSortBy(sortOptions, sorting);
    showSortedItems();
  }

});