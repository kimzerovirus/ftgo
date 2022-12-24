




// containers.forEach(c => console.log(c.attributes))

const draggables = document.querySelectorAll('.draggable')
const containers = document.querySelectorAll('.container')
const mainCategory = document.querySelectorAll('.main-category')
const main = document.querySelector('.main')


draggables.forEach(draggable => addDragEvent(draggable, 'dragging'))
mainCategory.forEach(draggable => addDragEvent(draggable, 'dragging-parent'))

containers.forEach(container => {
  container.addEventListener('dragover', e => {
    e.preventDefault()
    const afterElement = getDragAfterElement(container, e.clientY, '.draggable:not(.dragging)')
    const draggable = document.querySelector('.dragging')

    if(draggable !== null){
      if (afterElement == null) {
        container.appendChild(draggable)
      } else {
        container.insertBefore(draggable, afterElement)
      }
    }
  })
})

main.addEventListener('dragover', e =>{
  e.preventDefault()
  const afterElement = getDragAfterElement(main, e.clientY, '.main-category:not(.dragging)')
  const draggable = document.querySelector('.dragging-parent')

  if(draggable !== null){
    if (afterElement == null) {
      main.appendChild(draggable)
    } else {
      main.insertBefore(draggable, afterElement)
    }
  }
})



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
  el.addEventListener('dragstart', () => {
    el.classList.add(cls)
  })

  el.addEventListener('dragend', () => {
    el.classList.remove(cls)
  })
}