<template>
    <div class="container mt-0">
        <PrimePanel header="Cuentas">
            <div class="flex align-items-left justify-content-left">
                <h4>Cliente {{ cliente.nombre }} {{ cliente.apellido }}</h4>
            </div>
            <PrimeToolbar class="mb-4">
                <template #start>
                    <GoBack />
                    <PrimeButton label="Nueva Cuenta" icon="pi pi-plus" severity="success" class="mr-2" @click="openNew" />
                </template>
            </PrimeToolbar>
            <DataTable :value="cuentas" paginator :rows="10" tableStyle="min-width: 50rem">
                <PrimeColumn field="id" header="Id"></PrimeColumn>
                <PrimeColumn field="tipocuentaid" header="Tipo de Cuenta">
                    <template #body="{ data }">
                        {{ setTipoCuenta(data.tipocuentaid) }}
                    </template>
                </PrimeColumn>
                <PrimeColumn field="numero" header="Numero"></PrimeColumn>
                <PrimeColumn field="saldo" header="Saldo">
                    <template #body="{ data }">
                        {{ data.saldo.toLocaleString('es-ES', { style: 'currency', currency: 'GTQ' }) }}
                    </template>
                </PrimeColumn>
                <PrimeColumn :exportable="false" style="min-width:8rem">
                    <template #body="slotProps">
                        <PrimeButton icon="pi pi-pencil" outlined rounded class="mr-2" title="Editar Chequeras"
                            @click="editCuenta(slotProps.data)" />
                        <PrimeButton icon="pi pi-trash" outlined rounded severity="danger" class="mr-2" title="Eliminar Chequeras"
                            @click="confirmDeleteCuenta(slotProps.data)" />
                        <router-link v-if="slotProps.data.tipocuentaid == 1" :to="{ name: 'cuentachequeras', params: { id: slotProps.data.id } }">
                            <PrimeButton icon="pi pi-eye" outlined rounded severity="secondary" class="mr-2"
                                title="Ver Chequeras" />
                        </router-link>
                    </template>
                </PrimeColumn>
            </DataTable>
            <span v-if="cuentas != null && cuentas.length == 0">No hay cuentas asociadas</span>
        </PrimePanel>

        <PrimeDialog v-model:visible="cuentaDialog" :style="{ width: '450px' }" header="Detalle de Cuenta" :modal="true"
            class="p-fluid">
            <div class="field">
                <label for="saldo">Saldo</label>
                <InputText id="saldo" type="text" v-model.trim="cuenta.saldo" required="true" autofocus />
                <small class="p-error" v-if="submitted && !cuenta.saldo">Name is required.</small>
            </div>
            <div class="field">
                <label for="tipo">Tipo de Cuenta</label>
                <PrimeDropdown id="tipo" v-model="cuenta.tipocuentaid" :options="tiposCuenta" optionLabel="label"
                    optionValue="value" placeholder="Seleccione el tipo de cuenta" :disabled="cuenta.id != null" />
            </div>

            <template #footer>
                <PrimeButton label="Cancelar" icon="pi pi-times" text @click="hideDialog" />
                <PrimeButton label="Guardar" icon="pi pi-check" text @click="saveCuenta" />
            </template>
        </PrimeDialog>

        <PrimeDialog v-model:visible="deleteCuentaDialog" :style="{ width: '450px' }" header="Confirm" :modal="true">
            <div class="confirmation-content">
                <i class="pi pi-exclamation-triangle mr-3" style="font-size: 2rem" />
                <span v-if="cuenta">¿Seguro que desea eliminar la cuenta No. <b>{{ cuenta.numero }}</b>?</span>
            </div>
            <template #footer>
                <PrimeButton label="No" icon="pi pi-times" text @click="deleteCuentaDialog = false" />
                <PrimeButton label="Sí" icon="pi pi-check" text @click="deleteCuenta" />
            </template>
        </PrimeDialog>

        <PrimeToast ref="toast" position="bottom-right" />
    </div>
</template>

<script>
import { useToast } from 'primevue/usetoast';
import CuentaService from '../Services/CuentaService';
import ClienteService from '@/Services/ClienteService';
import GoBack from '../components/GoBack.vue';

export default {
    name: 'CrudApp',
    components: {
        GoBack
    },
    data() {
        return {
            toast: useToast(),
            cuentas: null,
            cuentaDialog: false,
            deleteCuentaDialog: false,
            cuenta: {},
            cliente: {}, 
            submitted: false,
            clienteId: this.$route.params.id,
            tiposCuenta: [
                { label: 'Monetaria', value: 1 },
                { label: 'Ahorro', value: 2 }]
        }
    },
    cuentaService: null,
    clienteService: null,
    created() {
        this.cuentaService = new CuentaService();
        this.clienteService = new ClienteService();
    },
    mounted() {
        this.cuentaService.getCuentasCliente(this.clienteId).then(data => {
            this.cuentas = data.data;
        });
        this.clienteService.getClient(this.clienteId).then(data => {
            this.cliente = data.data;
        });
    },
    methods: {
        openNew() {
            this.cuenta = {};
            this.submitted = false;
            this.cuentaDialog = true;
        },
        hideDialog() {
            this.cuentaDialog = false;
            this.submitted = false;
        },
        saveCuenta() {
            this.submitted = true;
            if (this.cuenta.id) {
                this.cuentaService.updateCuenta(this.cuenta).then(data => {
                    this.cuentas[this.cuentas.findIndex((cuenta) => cuenta.id === data.data.id)] = data.data;
                    this.toast.add({ severity: 'success', summary: 'Exitoso', detail: 'Cliente Actualizado', life: 3000 });
                }).catch(error => {
                    console.log(error)
                });
            } else {
                this.cuenta.tipocuentaid = this.cuenta.tipocuentaid ? this.cuenta.tipocuentaid.value : 1;
                this.cuenta.clienteid = this.clienteId;
                this.cuenta.numero = Math.floor(Math.random() * 1000000000);
                this.cuentaService.createCuenta(this.cuenta).then(data => {
                    this.cuentas.push(data.data);
                    this.toast.add({ severity: 'success', summary: 'Exitoso', detail: 'Cliente Creado', life: 3000 });
                }).catch(error => {
                    console.log(error)
                });
                console.log(this.cuenta);
            }

            this.cuentaDialog = false;
            this.cuenta = {};
        },
        editCuenta(cuenta) {
            this.cuenta = { ...cuenta };
            this.cuentaDialog = true;
        },
        confirmDeleteCuenta(cuenta) {
            this.cuenta = cuenta;
            this.deleteCuentaDialog = true;
        },
        deleteCuenta() {
            this.cuentaService.deleteCuenta(this.cuenta.id).then(() => {
                this.cuentas = this.cuentas.filter(val => val.id !== this.cuenta.id);
                this.deleteCuentaDialog = false;
                this.cuenta = {};
                this.toast.add({ severity: 'success', summary: 'Exitoso', detail: 'Cliente Eliminado', life: 3000 });
            }).catch(error => {
                console.log(error)
            });
        },
        setTipoCuenta(tipoCuentaId) {
            switch (tipoCuentaId) {
                case 1:
                    return 'Monetaria';
                case 2:
                    return 'Ahorro';
                default:
                    return 'No definido';
            }
        }
    }
}
</script>

<style scoped></style>