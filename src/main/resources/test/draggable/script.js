const draggables = document.querySelectorAll('.draggable')
const containers = document.querySelectorAll('.container')
const mainCategories = document.querySelectorAll('.main-category')
const main = document.querySelector('.main')

const dummyMain = [
  {id: 0, categoryNm: "여자", parentId: -1, orderId: 1},
  {id: 1, categoryNm: "남자", parentId: -1, orderId: 2},
]
const dummySub = [
  {id: 2, categoryNm: "상의", parentId: 0, orderId: 1},
  {id: 3, categoryNm: "상의", parentId: 1, orderId: 1},
  {id: 4, categoryNm: "하의", parentId: 1, orderId: 2},
]

createDom();

function createDom(){
  dummyMain.forEach(mainCategory => {
    const parentDiv = document.createElement('div');
    parentDiv.classList.add('main-category');
    setCategoryAttributes(parentDiv, mainCategory)
    parentDiv.innerHTML = mainCategory.categoryNm;

    const childDiv = document.createElement('div');
    childDiv.classList.add('container');

    const subDivWrapper = parentDiv.appendChild(childDiv);
    dummySub.forEach(subCategory => {
      if(mainCategory.id === subCategory.parentId){
        const div = document.createElement('div');
        div.classList.add('draggable');
        setCategoryAttributes(div, subCategory)
        div.innerHTML = subCategory.categoryNm;
        subDivWrapper.appendChild(div);
        addDragEvent(div, 'dragging')
      }
    })

    main.appendChild(parentDiv);
    addDragEvent(parentDiv, 'dragging-parent')
    manageCategories(childDiv, '.draggable','.dragging')
  })
}

draggables.forEach(draggable => addDragEvent(draggable, 'dragging'))
mainCategories.forEach(draggable => addDragEvent(draggable, 'dragging-parent'))

containers.forEach(container => manageCategories(container, '.draggable', '.dragging'))
manageCategories(main, '.main-category', '.dragging-parent');

function manageCategories(el, selectedClsname, dragClsname){
  el.addEventListener('dragover', e => {
    e.preventDefault(); // 이거 안하면 드래그 했다가 다시 놓았을 때 원래 자리로 돌아가는 잔상이 생김
    const afterElement = getDragAfterElement(el, e.clientY, selectedClsname + ':not(.dragging)')
    const draggable = document.querySelector(dragClsname)
  
    if(draggable !== null) {
      // console.log(afterElement)
      if (afterElement == null) {
        el.appendChild(draggable)
      } else {
        el.insertBefore(draggable, afterElement)
      }
    }
  })
}

function getDragAfterElement(el, y, cls) {
  const draggableElements = [...el.querySelectorAll(cls)]

  return draggableElements.reduce((closest, child) => {
    const box = child.getBoundingClientRect()
    const offset = y - box.top - box.height / 2
    if (offset < 0 && offset > closest.offset) {
      return { offset: offset, element: child }
    } else {
      return closest
    }
  }, { offset: Number.NEGATIVE_INFINITY }).element
}

function addDragEvent(el, cls){
  el.addEventListener('dragstart', (e) => {
    e.stopPropagation();
    el.classList.add(cls)
  })

  el.addEventListener('dragend', (e) => {
    e.stopPropagation();
    el.classList.remove(cls)
  })
}

function setCategoryAttributes(el, data){
  el.setAttribute('draggable', 'true')
  el.id = data.id
  el.categoryNm = data.categoryNm
  el.parentId = data.parentId
  el.orderId = data.orderId
  el.isVisible = data.isVisible
}

const show = document.getElementById('show')
show.addEventListener('click', () => {
  const mainCategoryList = [];
  const subCategoryList = [];
  let mainCategoryOrder = 0;
  let subCategoryOrder = 0;

  Array.from(main.children).forEach(el => {
    Array.from(el.children).forEach(el => 
      Array.from(el.children).forEach(el => subCategoryList.push({
        id: el.id,
        categoryNm: el.categoryNm,
        parentId: el.parentId,
        orderId: subCategoryOrder+=1,
        isVisible: el.isVisible,
      })
    ))

    mainCategoryList.push({
      id: el.id,
      categoryNm: el.categoryNm,
      parentId: el.parentId,
      orderId: mainCategoryOrder+=1,
      isVisible: el.isVisible,
    })
  })

  console.log(subCategoryList)
  console.log(mainCategoryList)
})