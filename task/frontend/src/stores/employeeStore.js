import {defineStore} from 'pinia'
import * as employeeService from "@/services/EmployeeService.js";

export const useEmployeeStore = defineStore('employee', {
  state: () => ({
    employeeRecordsForProject: [],
    loadingEmployeeRecordsForProject: false,
    employeeRecords: [],
    loadingEmployeeRecords: false,
    employeeRecordResult: {}
  }),
  actions: {
    async loadEmployeeRecords(fileName) {
      try {
        this.loadingEmployeeRecords = true
        await employeeService.getEmployeeRecords(fileName)
          .then((response) => {
            this.employeeRecords = response.data
          })
      } catch (error) {
        console.error('Error loading employee records:', error)
      } finally {
        this.loadingEmployeeRecords = false
      }
    },
    async loadEmployeeRecordResult(fileName) {
      try {
        await employeeService.getEmployeeRecordResult(fileName)
          .then((response) => {
            this.employeeRecordResult = response.data
          })
      } catch (error) {
        console.error('Error loading employee record result:', error)
      }
    },
    async loadEmployeeRecordsForProject(fileName) {
      try {
        this.loadingEmployeeRecordsForProject = true
        await employeeService.getEmployeeRecordsForProject(fileName)
          .then((response) => {
            this.employeeRecordsForProject = response.data
          })
      } catch (error) {
        console.error('Error loading employee records:', error)
      } finally {
        this.loadingEmployeeRecordsForProject = false
      }
    }
  }
})
