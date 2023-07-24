<template>
    
    <div class="container">
        <PrimePanel header="Cheques">
            <div class="flex align-items-left justify-content-left">
                <h4>Chequera No. {{ chequeraId }}</h4>
            </div>
            <PrimeToolbar class="mb-4">
                <template #start>
                    <GoBack />
                    <PrimeButton label="Emitir Cheque" icon="pi pi-plus" severity="success" class="mr-2" @click="openNew" />
                </template>
            </PrimeToolbar>

            <DataTable :value="cheques" paginator :rows="10" tableStyle="min-width: 50rem">
                <PrimeColumn field="id" header="Id"></PrimeColumn>
                <PrimeColumn field="fecha" header="Fecha de Emisión">
                    <template #body="{ data }">
                        {{ formatDate(data.fecha) }}
                    </template>
                </PrimeColumn>
                <PrimeColumn field="lugar" header="Lugar"></PrimeColumn>
                <PrimeColumn field="monto" header="Monto">
                    <template #body="{ data }">
                        {{ data.monto.toLocaleString('es-ES', { style: 'currency', currency: 'GTQ' }) }}
                    </template>
                </PrimeColumn>
                <PrimeColumn field="estado" header="Estado">
                    <template #body="{ data }">
                        {{ setEstadoCheque(data.estado) }}
                    </template>
                </PrimeColumn>
                <PrimeColumn :exportable="false" style="min-width:8rem">
                    <template #body="slotProps">
                        <PrimeButton icon="pi pi-money-bill" outlined rounded severity="success" class="mr-2"
                            title="Pagar Cheque" @click="confirmPagarCheque(slotProps.data)"
                            v-if="slotProps.data.estado == 0" />
                        <PrimeButton icon="pi pi-times" outlined rounded severity="danger" class="mr-2"
                            title="Cancelar Cheque" @click="confirmCancelarCheque(slotProps.data)"
                            v-if="slotProps.data.estado == 0" />
                    </template>
                </PrimeColumn>
            </DataTable>

            <span v-if="cheques != null && cheques.length == 0">No hay cheques emitidos</span>
        </PrimePanel>

        <PrimeDialog v-model:visible="chequeDialog" :style="{ width: '450px' }" header="Detalle de Cheque" :modal="true"
            class="p-fluid">
            <div class="field">
                <label for="lugar">Lugar</label>
                <InputText id="lugar" type="text" step="1" v-model.trim="cheque.lugar" required="true" autofocus />
                <small class="p-error" v-if="submitted && !cheque.lugar">Name is required.</small>
            </div>
            <div class="field">
                <label for="fecha">Fecha</label>
                <PrimeCalendar id="fecha" v-model="cheque.fecha" />
            </div>
            <div class="field">
                <label for="monto">Monto</label>
                <InputText id="monto" type="number" v-model.trim="cheque.monto" required="true" autofocus />
                <small class="p-error" v-if="submitted && !cheque.monto">Name is required.</small>
            </div>

            <template #footer>
                <PrimeButton label="Cancelar" icon="pi pi-times" text @click="hideDialog" />
                <PrimeButton label="Guardar" icon="pi pi-check" text @click="saveCheque" />
            </template>
        </PrimeDialog>

        <PrimeDialog v-model:visible="pagarChequeDialog" :style="{ width: '450px' }" header="Confirm" :modal="true">
            <div class="confirmation-content">
                <i class="pi pi-exclamation-triangle mr-3" style="font-size: 2rem" />
                <span v-if="cheque">¿Seguro que desea pagar el cheque No. <b>{{ cheque.id }}</b>?</span>
            </div>
            <template #footer>
                <PrimeButton label="No" icon="pi pi-times" text @click="pagarChequeDialog = false" />
                <PrimeButton label="Sí" icon="pi pi-check" text @click="pagarCheque" />
            </template>
        </PrimeDialog>

        <PrimeDialog v-model:visible="cancelarChequeDialog" :style="{ width: '450px' }" header="Confirm" :modal="true">
            <div class="confirmation-content">
                <i class="pi pi-exclamation-triangle mr-3" style="font-size: 2rem" />
                <span v-if="cheque">¿Seguro que desea eliminar la cheque No. <b>{{ cheque.id }}</b>?</span>
            </div>
            <template #footer>
                <PrimeButton label="No" icon="pi pi-times" text @click="cancelarChequeDialog = false" />
                <PrimeButton label="Sí" icon="pi pi-check" text @click="cancelarCheque" />
            </template>
        </PrimeDialog>

        <PrimeToast ref="toast" position="bottom-right" />
    </div>
</template>

<script>
import { useToast } from 'primevue/usetoast';
import ChequeService from '../Services/ChequeService';

export default {
    name: 'CrudApp',
    data() {
        return {
            toast: useToast(),
            cheques: null,
            chequeDialog: false,
            cancelarChequeDialog: false,
            pagarChequeDialog: false,
            cheque: {},
            submitted: false,
            chequeraId: this.$route.params.id,
            tiposCheque: [
                { label: 'Monetaria', value: 1 },
                { label: 'Ahorro', value: 2 }
            ]
        }
    },
    chequeService: null,
    created() {
        this.chequeService = new ChequeService();
    },
    mounted() {
        this.chequeService.getChequesCuenta(this.chequeraId).then(data => {
            this.cheques = data.data;
            console.log(this.cheques);
        });
    },
    methods: {
        openNew() {
            this.cheque = {};
            this.submitted = false;
            this.chequeDialog = true;
        },
        hideDialog() {
            this.chequeDialog = false;
            this.submitted = false;
        },
        saveCheque() {
            this.cheque.chequeraid = this.chequeraId;
            this.cheque.numero = "123456789";
            this.chequeService.createCheque(this.cheque).then(data => {
                this.cheques.push(data.data);
                this.toast.add({ severity: 'success', summary: 'Exitoso', detail: 'Cliente Creado', life: 3000 });
            }).catch(error => {
                console.log(error)
            });

            this.chequeDialog = false;
            this.cheque = {};
        },
        confirmPagarCheque(cheque) {
            this.cheque = cheque;
            this.pagarChequeDialog = true;
        },
        confirmCancelarCheque(cheque) {
            this.cheque = cheque;
            this.cancelarChequeDialog = true;
        },
        pagarCheque() {
            this.chequeService.pagarCheque(this.cheque).then((data) => {
                console.log(data.data);
                if (data.data.id) {
                    this.cheques[this.cheques.findIndex((cheque) => cheque.id === data.data.id)] = data.data;
                    this.pagarChequeDialog = false;
                    this.cheque = {};
                    this.toast.add({ severity: 'success', summary: 'Exitoso', detail: 'Cheque Pagado', life: 3000 });
                }
                else {
                    this.pagarChequeDialog = false;
                    this.cheque = {};
                    this.toast.add({ severity: 'error', summary: 'Error', detail: 'No se puede pagar el cheque', life: 3000 });
                }
            }).catch(error => {
                console.log(error)
            });
        },
        cancelarCheque() {
            this.chequeService.cancelarCheque(this.cheque).then((data) => {
                console.log(data.data);
                this.cheques[this.cheques.findIndex((cheque) => cheque.id === data.data.id)] = data.data;
                this.cancelarChequeDialog = false;
                this.cheque = {};
                this.toast.add({ severity: 'success', summary: 'Exitoso', detail: 'Cheque Cancelado', life: 3000 });
            }).catch(error => {
                console.log(error)
            });
        },
        setEstadoCheque(tipoChequeId) {
            switch (tipoChequeId) {
                case 1:
                    return 'Pagado';
                case 2:
                    return 'Cancelado';
                default:
                    return 'Emitido';
            }
        },
        formatDate(dateString) {
            const dateObj = new Date(dateString);
            const day = String(dateObj.getDate()).padStart(2, '0');
            const month = String(dateObj.getMonth() + 1).padStart(2, '0');
            const year = dateObj.getFullYear();
            return `${day}/${month}/${year}`;
        }
    }
}
</script>

<style scoped></style>
