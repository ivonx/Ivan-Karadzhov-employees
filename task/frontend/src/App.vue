<template>
  <v-app>
    <v-main>
      <div class="d-flex justify-center">
        <div class="autocomplete-width">
          <v-autocomplete
            v-model="selectedFile"
            :items="fileStore.csvFiles"
            :loading="fileStore.loadingCSVFiles"
            class="pt-13"
            label="Choose CSV file"
            underlined
            dense
          ></v-autocomplete>
        </div>
        <h3 v-if="employeeStore.employeePairRecords.length !== 0" class="pt-16 pl-2">
          Employee Pair worked together on common projects for the longest period of time:
          EmployeeId1: <strong>{{
            employeeStore.employeePairResult.employeePair?.firstEmployeeId
          }}</strong>,
          EmployeeId2: <strong>{{
            employeeStore.employeePairResult.employeePair?.secondEmployeeId
          }}</strong>,
          Days worked together: <strong>{{
            employeeStore.employeePairResult.daysWorkedTogether
          }}</strong>
        </h3>
      </div>
      <div>
        <div class="pb-2">
          <employee-pair-records-table
            :items="employeeStore.employeePairRecords"></employee-pair-records-table>
        </div>
        <div>
          <employee-records-for-project-table
            :items="employeeStore.employeeRecords"></employee-records-for-project-table>
        </div>
      </div>
    </v-main>
  </v-app>
</template>

<script setup>
import {onMounted, ref, watch} from 'vue'
import {useFileStore} from './stores/filesStore.js'
import {useEmployeeStore} from './stores/employeeStore.js'
import EmployeeRecordsForProjectTable from "@/components/EmployeeRecordsForProjectTable.vue";
import EmployeePairRecordsTable from "@/components/EmployeePairRecordsTable.vue";

const fileStore = useFileStore()
const employeeStore = useEmployeeStore()

onMounted(async () => {
  await fileStore.loadCSVFiles()
})
const selectedFile = ref(null)

watch(selectedFile, async (newFile) => {
  if (newFile) {
    await employeeStore.loadEmployeeRecordsForProject(newFile)
    await employeeStore.loadEmployeePairRecords(newFile)
    await employeeStore.loadEmployeePairResult(newFile)
  }
})
</script>

<style scoped>
.autocomplete-width {
  width: 400px;
}
</style>
