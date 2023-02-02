export function saveToLocalStorage(key, value) {
    localStorage.setItem(key, value)
}

export function getFromLocalStorage(key) {
    return localStorage.getItem(key)
}

export function removeFromLocalStorage(key) {
    localStorage.removeItem(key)
}
