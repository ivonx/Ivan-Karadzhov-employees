import axios from 'axios'

export const api = axios.create({
  timeout: 6000,
  params: {}
})

export function getEmployeeRecords(fileName) {
  return new Promise((resolve, reject) => {
    api.get(`http://localhost:8080/api/employees/records/${fileName}`)
      .then((response) => {
        resolve(response)
      }).catch((error) => {
      reject(error)
    })
  })
}

export function getEmployeeRecordResult(fileName) {
  return new Promise((resolve, reject) => {
    api.get(`http://localhost:8080/api/employees/record/${fileName}`)
      .then((response) => {
        resolve(response)
      }).catch((error) => {
      reject(error)
    })
  })
}

export function getEmployeeRecordsForProject(fileName) {
  return new Promise((resolve, reject) => {
    api.get(`http://localhost:8080/api/employees/project-records/${fileName}`)
      .then((response) => {
        resolve(response)
      }).catch((error) => {
      reject(error)
    })
  })
}
