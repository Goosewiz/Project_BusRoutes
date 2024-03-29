//export
const api = {
  async getCatalog(page, pageSize, sorting, filterBy) {
    let items = mockCatalog.items;
    if (filterBy) {
      items = items.filter(
        (i) =>
          i.title == filterBy || i.title.match(new RegExp(filterBy, "gi"))
      );
    }
    if (sorting) {
      items = sorted(items, sorting);
    }
    return items.slice(page * pageSize, (page + 1) * pageSize);
  },

  async getPagesCount(pageSize) {
  return Math.ceil(mockCatalog.items.length / pageSize);
  }
};