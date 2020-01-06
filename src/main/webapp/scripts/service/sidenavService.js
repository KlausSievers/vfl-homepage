app.factory('sidenavServ', function () {

  var itemSets = {};
  var title = "";
  var currentItems = [];
  var teamSelectable = false;


   function addItemToSet(itemset, title, fn) {
    getItemset(itemset).items.push({title: title, fn: fn});
  }

  function addDividerToSet(itemset) {
    getItemset(itemset).items.push({});
  }
  
  function setTitleToSet(itemset, title){
    getItemset(itemset).title = title;
  }

  function getItemset(itemset) {
    if (!itemSets[itemset]) {
      itemSets[itemset] = {title: "", items: []};
    }
    return itemSets[itemset];
  }

  function addItem(title, fn) {
    currentItems.push({title: title, fn: fn});
  }

  function addDivider() {
    currentItems.push({});
  }

  function clear() {
    currentItems = [];
  }

  function getItems() {
    return currentItems;
  }
  
  function setTitle(newTitle){
    title = newTitle;
  }
  
  function getTitle() {
    return title;
  }

  function setItemset(itemset) {
    if (!itemSets[itemset]) {
      throw new Error("Unknon itemset " + itemset);
    }

    clear();
    currentItems = angular.copy(itemSets[itemset].items);
    title = itemSets[itemset].title;
    teamSelectable = itemSets[itemset].teamSelectable;
  }

  function setTeamSelectable(itemset, selectable){
    getItemset(itemset).teamSelectable = selectable;
  }

  function isTeamSelectable() {
    return teamSelectable;
  }

  return {
    addItem: addItem,
    addDivider: addDivider,
    getItems: getItems,
    clear: clear,
    setItemset: setItemset,
    addItemToSet: addItemToSet,
    addDividerToSet: addDividerToSet,
    setTitleToSet: setTitleToSet,
    setTitle: setTitle,
    getTitle: getTitle,
    setTeamSelectable: setTeamSelectable,
    isTeamSelectable: isTeamSelectable
  };
});

