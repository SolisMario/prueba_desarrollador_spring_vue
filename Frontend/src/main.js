import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router';
import "primevue/resources/themes/lara-light-blue/theme.css";
import 'primeicons/primeicons.css';
import 'primeflex/primeflex.css';

import PrimeVue from 'primevue/config';
import App from './App.vue'
import ClientesLista from './Views/ClientesLista.vue'
import ClienteCuentas from './Views/ClienteCuentas.vue'
import CuentaChequeras from './Views/CuentaChequeras.vue'
import ChequeraCheques from "./Views/ChequeraCheques.vue";

import GoBack from './components/GoBack.vue';

import DataTable from 'primevue/datatable';
import PrimeColumn from 'primevue/column';
import PrimeButton from 'primevue/button';
import PrimeDialog from 'primevue/dialog';
import PrimeToolbar from 'primevue/toolbar';
import InputText from 'primevue/inputtext';
import PrimeToast from 'primevue/toast';
import ToastService from 'primevue/toastservice';
import PrimePanel from 'primevue/panel';
import PrimeDropdown from 'primevue/dropdown';
import PrimeMenubar from 'primevue/menubar';
import PrimeTag from 'primevue/tag';
import PrimeCard from 'primevue/card';
import PrimeCalendar from 'primevue/calendar';

const routes = [
    { path: '/', component: ClientesLista },
    { path: '/clientecuentas/:id', component: ClienteCuentas, props: true, name: 'clientecuentas' },
    { path: '/cuentachequeras/:id', component: CuentaChequeras, props: true, name: 'cuentachequeras' },
    { path: '/chequeracheques/:id', component: ChequeraCheques, props: true, name: 'chequeracheques' },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

const app = createApp(App);
app.use(PrimeVue);
app.use(router);
app.use(ToastService);

app.component('DataTable', DataTable);
app.component('PrimeColumn', PrimeColumn);
app.component('PrimeButton', PrimeButton);
app.component('PrimeDialog', PrimeDialog);
app.component('PrimeToolbar', PrimeToolbar);
app.component('InputText', InputText);
app.component('PrimeToast', PrimeToast);
app.component('PrimePanel', PrimePanel);
app.component('PrimeDropdown', PrimeDropdown);
app.component('PrimeTag', PrimeTag);
app.component('PrimeMenubar', PrimeMenubar);
app.component('PrimeCard', PrimeCard);
app.component('GoBack', GoBack);
app.component('PrimeCalendar', PrimeCalendar);

app.mount('#app');