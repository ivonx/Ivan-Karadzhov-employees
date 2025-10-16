import axios from 'axios'

export const api = axios.create({
  timeout: 6000,
  params: {}
})

export function getCSVFileNames() {
  return new Promise((resolve, reject) => {
    api.get('http://localhost:8080/api/files')
      .then((response) => {
        resolve(response)
      }).catch((error) => {
      reject(error)
    })
  })
}
