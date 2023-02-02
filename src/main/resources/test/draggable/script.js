// mainCategoryList.filter(count => count.isVisible == true).length 메인 카테고리는 4개 까지 visible 이어야 한다.
// 카테고리의 네임이 모두 있어야 한다 없을 경우 저장할 수 없다.
// id -> categoryId 로 변경해야 할듯 
// entity 에서 dto 로 바꿀 때 id -> categoryId 로 이름 변경



const draggables = document.querySelectorAll('.draggable')
const containers = document.querySelectorAll('.draggable-container')
const mainCategories = document.querySelectorAll('.main-category')
const main = document.querySelector('.draggable-category')

const dummyMain = [
  {categoryId: 0, categoryNm: "여자", parentId: -1, orderId: 1, isVisible: true, isDel: false},
  {categoryId: 1, categoryNm: "남자", parentId: -1, orderId: 2, isVisible: true},
]
const dummySub = [
  {categoryId: 2, categoryNm: "여-상의", parentId: 0, orderId: 1},
  {categoryId: 3, categoryNm: "남-상의", parentId: 1, orderId: 1},
  {categoryId: 4, categoryNm: "남-하의", parentId: 1, orderId: 2},
]

createDom();

function createDom(){
  dummyMain.forEach(mainCategory => {
    const parentDiv = document.createElement('div');
    parentDiv.classList.add('main-category');
    setCategoryAttributes(parentDiv, mainCategory)

    const categoryNmDiv = document.createElement('div');
    categoryNmDiv.classList.add('input-group');
    categoryNmDiv.classList.add('draggable');
    
    const categoryNmInput = document.createElement('input');
    const addBtn = document.createElement('button')
    const editBtn = document.createElement('button')
    const hideBtn = document.createElement('button')
    const deleteBtn = document.createElement('button')

    addBtn.classList.add('btn')
    editBtn.classList.add('btn')
    hideBtn.classList.add('btn')
    deleteBtn.classList.add('btn')

    
    categoryNmInput.classList.add('form-control');
    categoryNmInput.setAttribute('value', mainCategory.categoryNm);
    categoryNmInput.setAttribute('readonly', 'true');
    categoryNmInput.addEventListener('input', e => {
      parentDiv.categoryNm = e.target.value
    })
    categoryNmInput.addEventListener('focusout', ()=> {
      categoryNmInput.setAttribute('readonly', 'true');
    })
    addBtn.innerText = '추가';
    editBtn.innerText = '수정';
    editBtn.addEventListener('click', () => {
      categoryNmInput.removeAttribute('readonly');
      const end = categoryNmInput.value.length;
      categoryNmInput.setSelectionRange(end, end); // 포커스시 커서 맨 끝으로 보내기
      categoryNmInput.focus();
    })
    if(parentDiv.isVisible) {
      hideBtn.innerText = '비공개';
    } else {
      hideBtn.innerText = '공개';
    }
    hideBtn.addEventListener('click', () => {
      parentDiv.isVisible = !parentDiv.isVisible;
      if(parentDiv.isVisible) {
        hideBtn.innerText = '비공개';
      } else {
        hideBtn.innerText = '공개';
      }
    })
    deleteBtn.innerText = '삭제';
    if(!parentDiv.isDel) deleteBtn.setAttribute('disabled', 'true')
    deleteBtn.addEventListener('click', ()=> {
      if(parentDiv.isDel) parentDiv.remove();
    })


    parentDiv.appendChild(categoryNmDiv);
    categoryNmDiv.appendChild(categoryNmInput);
    categoryNmDiv.appendChild(addBtn);
    categoryNmDiv.appendChild(editBtn);
    categoryNmDiv.appendChild(hideBtn);
    categoryNmDiv.appendChild(deleteBtn);

    const childDiv = document.createElement('div');
    childDiv.classList.add('draggable-container');

    const subDivWrapper = parentDiv.appendChild(childDiv);
    dummySub.forEach(subCategory => {
      if(mainCategory.categoryId === subCategory.parentId){
        const div = document.createElement('div');
        div.classList.add('draggable');
        setCategoryAttributes(div, subCategory)

        const categoryNmInput = document.createElement('input');
        const editBtn = document.createElement('button')
        const hideBtn = document.createElement('button')
        const deleteBtn = document.createElement('button')

        addBtn.classList.add('btn')
        editBtn.classList.add('btn')
        hideBtn.classList.add('btn')
        deleteBtn.classList.add('btn')

        categoryNmInput.classList.add('form-control');
        categoryNmInput.setAttribute('value', subCategory.categoryNm);
        categoryNmInput.setAttribute('readonly', 'true');
        categoryNmInput.addEventListener('input', e => {
          div.categoryNm = e.target.value
        })

        editBtn.innerText = '수정';
        editBtn.addEventListener('click', () => {
          categoryNmInput.removeAttribute('readonly');
          const end = categoryNmInput.value.length;
          categoryNmInput.setSelectionRange(end, end); // 포커스시 커서 맨 끝으로 보내기
          categoryNmInput.focus();
        })
        if(div.isVisible) {
          hideBtn.innerText = '비공개';
        } else {
          hideBtn.innerText = '공개';
        }
        hideBtn.addEventListener('click', () => {
          div.isVisible = !div.isVisible;
          if(div.isVisible) {
            hideBtn.innerText = '비공개';
          } else {
            hideBtn.innerText = '공개';
          }
        })
        deleteBtn.innerText = '삭제';
        if(!div.isDel) deleteBtn.setAttribute('disabled', 'true')
        deleteBtn.addEventListener('click', ()=> {
          if(div.isDel) div.remove(div);
        })
    
        categoryNmInput.addEventListener('focusout', ()=> {
          categoryNmInput.setAttribute('readonly', 'true');
        })

        div.appendChild(categoryNmInput);
        div.appendChild(editBtn);
        div.appendChild(hideBtn);
        div.appendChild(deleteBtn);

        subDivWrapper.appendChild(div);
        addDragEvent(div, 'dragging') // 각 요소마다 드래그 이벤트를 걸어줘야함
      }
    })

    // 서브 카테고리 추가 이벤트
    addBtn.addEventListener('click', () => {
      const div = document.createElement('div');
        div.classList.add('draggable');
        div.parentId = parentDiv.categoryId;
        div.isVisible = true;
        div.isDel = true;

        const categoryNmInput = document.createElement('input');
        const editBtn = document.createElement('button')
        const hideBtn = document.createElement('button')
        const deleteBtn = document.createElement('button')

        categoryNmInput.addEventListener('input', e => {
          div.categoryNm = e.target.value
        })

        editBtn.innerText = '수정';
        editBtn.addEventListener('click', () => {
          categoryNmInput.removeAttribute('readonly');
          const end = categoryNmInput.value.length;
          categoryNmInput.setSelectionRange(end, end); // 포커스시 커서 맨 끝으로 보내기
          categoryNmInput.focus();
        })
        if(div.isVisible) {
          hideBtn.innerText = '비공개';
        } else {
          hideBtn.innerText = '공개';
        }
        hideBtn.addEventListener('click', () => {
          div.isVisible = !div.isVisible;
          if(div.isVisible) {
            hideBtn.innerText = '비공개';
          } else {
            hideBtn.innerText = '공개';
          }
        })
        deleteBtn.innerText = '삭제';

        categoryNmInput.addEventListener('focusout', ()=> {
          categoryNmInput.setAttribute('readonly', 'true');
        })

        div.appendChild(categoryNmInput);
        div.appendChild(editBtn);
        div.appendChild(hideBtn);
        div.appendChild(deleteBtn);

        subDivWrapper.appendChild(div);
        div.setAttribute('draggable', 'true')
        addDragEvent(div, 'dragging');

        deleteBtn.addEventListener('click', ()=> {
          if(div.isDel) div.remove(div);
        })

        categoryNmInput.focus();
    })

    main.appendChild(parentDiv);
    addDragEvent(parentDiv, 'dragging-parent')
    manageCategories(childDiv, '.draggable','.dragging') // addDragEvent 와 달리 wrapper 클래스만 등록하면 감지 범위가 된다.
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
  el.classList.add('input-group')

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
  el.categoryId = data.categoryId
  el.categoryNm = data.categoryNm
  el.parentId = data.parentId
  el.orderId = data.orderId
  el.isVisible = data.isVisible
  el.isDel = data.isDel
}

const show = document.getElementById('show')
show.addEventListener('click', () => {
  const mainCategoryList = [];
  const subCategoryList = [];
  let mainCategoryOrder = 0;
  let subCategoryOrder = 0;

  Array.from(main.children).forEach(el => {
    Array.from(el.children).forEach(el => 
      Array.from(el.children).forEach(el => {
        if(el.classList.contains('draggable')){ // category input field 는 제거
          subCategoryList.push({
            categoryId: el.categoryId,
            categoryNm: el.categoryNm,
            parentId: el.parentId,
            orderId: subCategoryOrder+=1,
            isVisible: el.isVisible,
          })
        }  
      }
    ))

    mainCategoryList.push({
      categoryId: el.categoryId,
      categoryNm: el.categoryNm,
      parentId: el.parentId,
      orderId: mainCategoryOrder+=1,
      isVisible: el.isVisible,
    })
  })

  console.log(subCategoryList)
  console.log(mainCategoryList)
  const categoryList = [ ... subCategoryList, ... mainCategoryList]
  console.log(categoryList)
  if(categoryList.filter(category => category.categoryNm === null || category.categoryNm === '' || category.categoryNm === undefined).length > 0) alert('편집을 완료하신 뒤에 카테고리를 저장해주십시오')
})  
