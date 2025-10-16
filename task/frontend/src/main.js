import './assets/main.css'

import {createApp} from 'vue'
import {createPinia} from 'pinia'

import App from './App.vue'
import 'vuetify/styles'
import {createVuetify} from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

// Fonts & icons
import '@mdi/font/css/materialdesignicons.css'
import 'roboto-fontface/css/roboto/roboto-fontface.css'

const app = createApp(App)

app.use(createPinia())
// app.use(router)

const vuetify = createVuetify({
  components,
  directives,
  theme: {
    defaultTheme: 'light',
  },
})

app.use(vuetify)

app.mount('#app')
