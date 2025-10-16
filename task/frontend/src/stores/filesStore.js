import {defineStore} from 'pinia'
import * as fileService from "@/services/FileService.js";

export const useFileStore = defineStore('file', {
  state: () => ({
    csvFiles: [],
    loadingCSVFiles: false
  }),
  actions: {
    async loadCSVFiles() {
      try {
        this.loadingCSVFiles = true
        await fileService.getCSVFileNames()
          .then((response) => {
            this.csvFiles = response.data
          })
      } catch (error) {
        console.error('Error loading the csv file names:', error)
      } finally {
        this.loadingCSVFiles = false
      }
    }
  }
})
