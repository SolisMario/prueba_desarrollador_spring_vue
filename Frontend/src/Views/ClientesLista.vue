<template>
    <div class="container">
        <PrimePanel header="Clientes">
            <PrimeToolbar class="mb-4">
                <template #start>
                    <PrimeButton label="Nuevo Cliente" icon="pi pi-plus" severity="success" class="mr-2" @click="openNew" />
                </template>
            </PrimeToolbar>
            <DataTable :value="clients" paginator :rows="10" tableStyle="min-width: 50rem">
                <PrimeColumn field="id" header="Id"></PrimeColumn>
                <PrimeColumn field="nombre" header="Nombre"></PrimeColumn>
                <PrimeColumn field="apellido" header="Apellido"></PrimeColumn>
                <PrimeColumn field="correo" header="Correo"></PrimeColumn>
                <PrimeColumn field="direccion" header="Dirección"></PrimeColumn>
                <PrimeColumn field="telefono" header="Teléfono"></PrimeColumn>
                <PrimeColumn :exportable="false" style="min-width:8rem">
                    <template #body="slotProps">
                        <PrimeButton icon="pi pi-pencil" outlined rounded class="mr-2" title="Editar Cliente"
                            @click="editClient(slotProps.data)" />
                        <PrimeButton icon="pi pi-trash" outlined rounded severity="danger" class="mr-2" title="Eliminar Cliente"
                            @click="confirmDeleteClient(slotProps.data)" />
                        <router-link :to="{ name: 'clientecuentas', params: { id: slotProps.data.id } }">
                            <PrimeButton icon="pi pi-eye" outlined rounded severity="secondary" class="mr-2"
                                title="Ver Cuentas" />
                        </router-link>
                    </template>
                </PrimeColumn>
            </DataTable>
        </PrimePanel>

        <PrimeDialog v-model:visible="clientDialog" :style="{ width: '450px' }" header="Detalle de Cliente" :modal="true"
            class="p-fluid">
            <div class="field">
                <label for="name">Nombre</label>
                <InputText id="name" type="text" v-model.trim="client.nombre" required="true" autofocus
                    :class="{ 'p-invalid': submitted && !client.nombre }" />
                <small class="p-error" v-if="submitted && !client.nombre">Name is required.</small>
            </div>
            <div class="field">
                <label for="apellido">Apellido</label>
                <InputText id="apellido" type="text" v-model.trim="client.apellido" required="true" />
                <small class="p-error" v-if="submitted && !client.apellido">Name is required.</small>
            </div>
            <div class="field">
                <label for="direccion">Dirección</label>
                <InputText id="direccion" type="text" v-model.trim="client.direccion" required="true" />
                <small class="p-error" v-if="submitted && !client.direccion">Name is required.</small>
            </div>
            <div class="field">
                <label for="correo">Correo</label>
                <InputText id="direccion" type="email" v-model.trim="client.correo" required="true" />
                <small class="p-error" v-if="submitted && !client.correo">Name is required.</small>
            </div>
            <div class="field">
                <label for="telefono">Teléfono</label>
                <InputText id="telefono" type="text" v-model.trim="client.telefono" required="true" />
                <small class="p-error" v-if="submitted && !client.telefono">Name is required.</small>
            </div>

            <template #footer>
                <PrimeButton label="Cancelar" icon="pi pi-times" text @click="hideDialog" />
                <PrimeButton label="Guardar" icon="pi pi-check" text @click="saveClient" />
            </template>
        </PrimeDialog>

        <PrimeDialog v-model:visible="deleteClientDialog" :style="{ width: '450px' }" header="Confirm" :modal="true">
            <div class="confirmation-content">
                <i class="pi pi-exclamation-triangle mr-3" style="font-size: 2rem" />
                <span v-if="client">¿Seguro que desea eliminar a <b>{{ client.nombre }}</b>?</span>
            </div>
            <template #footer>
                <PrimeButton label="No" icon="pi pi-times" text @click="deleteClientDialog = false" />
                <PrimeButton label="Yes" icon="pi pi-check" text @click="deleteClient" />
            </template>
        </PrimeDialog>

        <PrimeToast ref="toast" position="bottom-right" />
    </div>
</template>

<script>
import { useToast } from 'primevue/usetoast';
import ClienteService from '../Services/ClienteService';



export default {
    name: 'CrudApp',
    data() {
        return {
            toast: useToast(),
            clients: null,
            clientDialog: false,
            deleteClientDialog: false,
            client: {},
            submitted: false
        }
    },
    clienteService: null,
    created() {
        this.clienteService = new ClienteService();
    },
    mounted() {
        this.clienteService.readClients().then(data => {
            this.clients = data.data;
        });
    },
    methods: {
        openNew() {
            this.client = {};
            this.submitted = false;
            this.clientDialog = true;
        },
        hideDialog() {
            this.clientDialog = false;
            this.submitted = false;
        },
        saveClient() {
            this.submitted = true;
            if (this.client.id) {
                this.clienteService.updateClient(this.client).then(data => {
                    console.log(data.data);
                    this.clients[this.clients.findIndex((client) => client.id === data.data.id)] = data.data;
                    this.toast.add({ severity: 'success', summary: 'Exitoso', detail: 'Cliente Actualizado', life: 3000 });
                }).catch(error => {
                    console.log(error)
                });
            } else {
                this.clienteService.createClient(this.client).then(data => {
                    this.clients.push(data.data);
                    this.toast.add({ severity: 'success', summary: 'Exitoso', detail: 'Cliente Creado', life: 3000 });
                }).catch(error => {
                    console.log(error)
                });
            }

            this.clientDialog = false;
            this.client = {};
        },
        editClient(client) {
            this.client = { ...client };
            this.clientDialog = true;
        },
        confirmDeleteClient(client) {
            this.client = client;
            this.deleteClientDialog = true;
        },
        deleteClient() {
            this.clienteService.deleteClient(this.client.id).then(() => {
                this.clients = this.clients.filter(val => val.id !== this.client.id);
                this.deleteClientDialog = false;
                this.client = {};
                this.toast.add({ severity: 'success', summary: 'Exitoso', detail: 'Cliente Eliminado', life: 3000 });
            }).catch(error => {
                console.log(error)
            });
        }
    }
}
</script>

<style scoped></style>
