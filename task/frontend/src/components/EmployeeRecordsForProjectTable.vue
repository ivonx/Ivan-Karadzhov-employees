<template>
  <v-card elevation="2">
    <v-card-title class="text-h5 font-weight-bold">
      Employee Pairs worked together for the longest period of time per project
    </v-card-title>

    <v-data-table
      :headers="headers"
      :items="items"
      :loading="employeeStore.loadingEmployeeRecordsForProject"
      class="elevation-1"
      density="comfortable"
    >
      <template #no-data>
        <v-alert type="info" class="ma-4" border="start" prominent>
          No employee pairs found yet. Please select a CSV file to analyze.
        </v-alert>
      </template>
    </v-data-table>
  </v-card>
</template>

<script setup>
import {computed} from 'vue'
import {useEmployeeStore} from "@/stores/employeeStore.js";

const employeeStore = useEmployeeStore()

defineProps({
  items: {
    type: Array,
    default: () => []
  }
})

const headers = computed(() => [
  {title: 'EmployeeId #1', key: 'employeePair.firstEmployeeId', align: 'center'},
  {title: 'EmployeeId #2', key: 'employeePair.secondEmployeeId', align: 'center'},
  {title: 'Project ID', key: 'projectId', align: 'center'},
  {title: 'Days Worked', key: 'daysWorkedTogether', align: 'center'}
])
</script>

<style scoped>
</style>
