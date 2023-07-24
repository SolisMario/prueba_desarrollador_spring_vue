<template>
    <div class="container">
        <PrimePanel header="Chequeras">
            <div class="flex align-items-left justify-content-left">
                <h4>Cuenta No. {{ cuentaId }}</h4>
            </div>
            <PrimeToolbar class="mb-4">
                <template #start>
                    <GoBack />
                    <PrimeButton label="Nueva Chequera" icon="pi pi-plus" severity="success" class="mr-2"
                        @click="openNew" />
                </template>
            </PrimeToolbar>

            <DataTable :value="chequeras" paginator :rows="10" tableStyle="min-width: 50rem">
                <PrimeColumn field="id" header="Id"></PrimeColumn>
                <PrimeColumn field="cantidadcheques" header="Cantidad de Cheques"></PrimeColumn>
                <PrimeColumn :exportable="false" style="min-width:8rem">
                    <template #body="slotProps">
                        <PrimeButton icon="pi pi-trash" outlined rounded severity="danger" class="mr-2" title="Eliminar Chequera"
                            @click="confirmDeleteChequera(slotProps.data)" />
                        <router-link :to="{ name: 'chequeracheques', params: { id: slotProps.data.id } }">
                            <PrimeButton icon="pi pi-eye" outlined rounded severity="secondary" class="mr-2"
                                title="Ver Cheques" />
                        </router-link>
                    </template>
                </PrimeColumn>
            </DataTable>

            <span v-if="chequeras != null && chequeras.length == 0">No hay cheques emitidos</span>
        </PrimePanel>

        <PrimeDialog v-model:visible="chequeraDialog" :style="{ width: '450px' }" header="Detalle de Chequera" :modal="true"
            class="p-fluid">
            <div class="field">
                <label for="cantidadcheques">Cantidad de Cheques</label>
                <InputText id="cantidadcheques" type="number" step="1" v-model.trim="chequera.cantidadcheques"
                    required="true" autofocus />
                <small class="p-error" v-if="submitted && !chequera.cantidadcheques">Name is required.</small>
            </div>

            <template #footer>
                <PrimeButton label="Cancelar" icon="pi pi-times" text @click="hideDialog" />
                <PrimeButton label="Guardar" icon="pi pi-check" text @click="saveChequera" />
            </template>
        </PrimeDialog>

        <PrimeDialog v-model:visible="deleteChequeraDialog" :style="{ width: '450px' }" header="Confirm" :modal="true">
            <div class="confirmation-content">
                <i class="pi pi-exclamation-triangle mr-3" style="font-size: 2rem" />
                <span v-if="chequera">¿Seguro que desea eliminar la chequera No. <b>{{ chequera.numero }}</b>?</span>
            </div>
            <template #footer>
                <PrimeButton label="No" icon="pi pi-times" text @click="deleteChequeraDialog = false" />
                <PrimeButton label="Sí" icon="pi pi-check" text @click="deleteChequera" />
            </template>
        </PrimeDialog>

        <PrimeToast ref="toast" position="bottom-right" />
    </div>
</template>

<script>
import { useToast } from 'primevue/usetoast';
import ChequeraService from '../Services/ChequeraService';

export default {
    name: 'CrudApp',
    data() {
        return {
            toast: useToast(),
            chequeras: null,
            chequeraDialog: false,
            deleteChequeraDialog: false,
            chequera: {},
            submitted: false,
            cuentaId: this.$route.params.id,
            tiposChequera: [
                { label: 'Monetaria', value: 1 },
                { label: 'Ahorro', value: 2 }
            ]
        }
    },
    chequeraService: null,
    created() {
        this.chequeraService = new ChequeraService();
    },
    mounted() {
        this.chequeraService.getChequerasCuenta(this.cuentaId).then(data => {
            this.chequeras = data.data;
        });
    },
    methods: {
        openNew() {
            this.chequera = {};
            this.submitted = false;
            this.chequeraDialog = true;
        },
        hideDialog() {
            this.chequeraDialog = false;
            this.submitted = false;
        },
        saveChequera() {
            this.submitted = true;
            if (this.chequera.id) {
                this.chequeraService.updateChequera(this.chequera).then(data => {
                    this.chequeras[this.chequeras.findIndex((chequera) => chequera.id === data.data.id)] = data.data;
                    this.toast.add({ severity: 'success', summary: 'Exitoso', detail: 'Cuenta Actualizada', life: 3000 });
                }).catch(error => {
                    console.log(error)
                });
            } else {
                this.chequera.cuentaid = this.cuentaId;
                this.chequera.numero = "123456789";
                this.chequeraService.createChequera(this.chequera).then(data => {
                    this.chequeras.push(data.data);
                    this.toast.add({ severity: 'success', summary: 'Exitoso', detail: 'Cuenta Creada', life: 3000 });
                }).catch(error => {
                    console.log(error)
                });
                console.log(this.chequera);
            }

            this.chequeraDialog = false;
            this.chequera = {};
        },
        editChequera(chequera) {
            this.chequera = { ...chequera };
            this.chequeraDialog = true;
        },
        confirmDeleteChequera(chequera) {
            this.chequera = chequera;
            this.deleteChequeraDialog = true;
        },
        deleteChequera() {
            this.chequeraService.deleteChequera(this.chequera.id).then(() => {
                this.chequeras = this.chequeras.filter(val => val.id !== this.chequera.id);
                this.deleteChequeraDialog = false;
                this.chequera = {};
                this.toast.add({ severity: 'success', summary: 'Exitoso', detail: 'Cuenta Eliminada', life: 3000 });
            }).catch(error => {
                console.log(error)
            });
        },
        setTipoChequera(tipoChequeraId) {
            switch (tipoChequeraId) {
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

<style scoped>
PrimePabel {
    margin-bottom: 2rem;
    background-color: var(--primary-color);
}
</style>
