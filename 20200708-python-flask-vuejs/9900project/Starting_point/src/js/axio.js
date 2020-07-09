import axios from 'axios'
export default async (url = '', data = {}, type = 'POST', isUrl = 0) => {
const basicUrl = 'http://127.0.0.1:5000'
// const basicUrl = 'http://127.0.0.1:3000'
//const basicUrl = 'http://47.93.31.176:5000'
  // const basicUrl = 'http://192.168.6.155:8081/servlet';
  type = type.toUpperCase()
  if (isUrl === 0) {
    url = basicUrl + url
  }
  try {
    if (type === 'GET') {
      const response = axios.get(url, {params: data})
      return response
    } else if (type === 'POST') {
      const response = axios.post(url, data, {headers: {'Content-Type': 'application/json'}})
      console.log('post:' + JSON.stringify(data))
      return response
    } else if (type === 'DELETE') {
      const response = axios({
        method: 'delete',
        url: url,
        params: data
      })
      console.log('delete:' + JSON.stringify(data))
      return response
    }
  } catch (error) {
    throw new Error(error)
  }
}
