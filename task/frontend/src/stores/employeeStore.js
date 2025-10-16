import {defineStore} from 'pinia'
import * as employeeService from "@/services/EmployeeService.js";

export const useEmployeeStore = defineStore('employee', {
  state: () => ({
    employeeRecords: [],
    loadingEmployeeRecordsForProject: false,
    employeePairRecords: [],
    loadingEmployeePairRecords: false,
    employeePairResult: {}
  }),
  actions: {
    async loadEmployeeRecordsForProject(fileName) {
      try {
        this.loadingEmployeeRecordsForProject = true
        await employeeService.getEmployeeRecordsForProject(fileName)
          .then((response) => {
            this.employeeRecords = response.data
          })
      } catch (error) {
        console.error('Error loading employee records:', error)
      } finally {
        this.loadingEmployeeRecordsForProject = false
      }
    },
    async loadEmployeePairRecords(fileName) {
      try {
        this.loadingEmployeePairRecords = true
        await employeeService.getEmployeePairRecords(fileName)
          .then((response) => {
            this.employeePairRecords = response.data
          })
      } catch (error) {
        console.error('Error loading employee records:', error)
      } finally {
        this.loadingEmployeePairRecords = false
      }
    },
    async loadEmployeePairResult(fileName) {
      try {
        await employeeService.getEmployeePairResult(fileName)
          .then((response) => {
            this.employeePairResult = response.data
          })
      } catch (error) {
        console.error('Error loading employee pair:', error)
      }
    }
  }
})
