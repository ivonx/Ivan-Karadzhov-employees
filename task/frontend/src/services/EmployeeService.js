import axios from 'axios'

export const api = axios.create({
  timeout: 6000,
  params: {}
})

export function getEmployeeRecordsForProject(fileName) {
  return new Promise((resolve, reject) => {
    api.get(`http://localhost:8080/api/employees/${fileName}`)
      .then((response) => {
        resolve(response)
      }).catch((error) => {
      reject(error)
    })
  })
}

export function getEmployeePairRecords(fileName) {
  return new Promise((resolve, reject) => {
    api.get(`http://localhost:8080/api/employees/pair-records/${fileName}`)
      .then((response) => {
        resolve(response)
      }).catch((error) => {
      reject(error)
    })
  })
}

export function getEmployeePairResult(fileName) {
  return new Promise((resolve, reject) => {
    api.get(`http://localhost:8080/api/employees/pair/${fileName}`)
      .then((response) => {
        resolve(response)
      }).catch((error) => {
      reject(error)
    })
  })
}
