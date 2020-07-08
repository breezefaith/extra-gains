import axio from '../js/axio'

export const register = data => axio('/register', data)

export const signin = data => axio('/signin', data)

export const collection = data => axio('/collection', data)

export const add_collection = data => axio('/add_collection', data)

export const rec_bookInfo = data => axio('/rec_bookInfo',data)

export const topten = data => axio('/topten', data)
