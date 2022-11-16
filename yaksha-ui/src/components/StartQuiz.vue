<template>
    <v-container>
        <v-row>
            <v-col>
                <v-btn block rounded x-large>Start QUiz</v-btn>
            </v-col>
        </v-row>
        <v-row>
            <v-col>
                <v-divider></v-divider>
            </v-col>
        </v-row>
        <v-row class="d-flex justify-left">
            <v-col cols="12" sm="6" md="6">
                <v-timeline align-top dense>
                    <v-timeline-item fill-dot icon="mdi-star" color="red">
                        <v-row>
                            <v-col>
                                <strong>Admin Created group</strong>
                                <div class="text-caption">
                                    Lets start as soon as everyone joins
                                </div>
                            </v-col>
                        </v-row>
                    </v-timeline-item>
                    <v-timeline-item fill-dot icon="mdi-account-multiple">
                        <v-row>
                            <v-col>
                                <strong>People are joining</strong>
                                <div class="text-caption">
                                    Lets start as soon as everyone joins
                                </div>
                            </v-col>
                        </v-row>
                    </v-timeline-item>
                </v-timeline>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    name: 'SatrtQuiz',
    data:()=>({
        stompClient:null
    }),
    created() {
        console.log(this.$route.params.groupId)
        
    }

}
</script>

<style>

</style>