<template>
  <v-container>
    <v-row justify="center" align-content="center">
      <v-col class="d-flex justify-center">
        <v-avatar color="primary" size="65">
          <v-icon dark>
            mdi-account-circle
          </v-icon>
        </v-avatar>
      </v-col>
    </v-row>
    <v-row justify="center">
      <h3>{{ userName }}</h3>
    </v-row>
    <v-row justify="center">
      <v-col cols="12" sm="6" md="3">
        <v-btn block elevation="2" rounded x-large @click="createGroup">
          Create group
        </v-btn>
      </v-col>

    </v-row>
    <v-row>
      <v-col>
        <v-spacer></v-spacer>
        <v-divider>Or</v-divider>
      </v-col>
    </v-row>

    <v-row justify="center" class="text-center">
      <v-col cols="12" sm="6" md="3">
        <v-otp-input length="4" v-model="groupId"></v-otp-input>
      </v-col>
    </v-row>

    <v-row justify="center" class="text-center">
      <v-col cols="12" sm="6" md="3">
        <v-btn block elevation="2" rounded x-large @click="joinGroup">
          Join group
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'CreateJoinComponent',

  data: () => ({
    groupId: "XXXX",
    userName: ''
  }),
  methods: {
    joinGroup: function () {
      console.log("Join group" + this.groupId)
    },
    createGroup: function () {
      console.log("Create Group")
      axios.post(this.$globalData.urls.createGroup, { "groupId": this.groupId, "fromUser": this.userName, "eventType": "CREATED" })
        .then((response) => {
          if (response.status != 200) {
            console.log("Something went wrong");
          }
          this.$router.push({ name: "startQuiz", params: { groupId: response.data.groupId } });
        })
        .catch(() => { console.log("something went wrong while creating group") })
    }
  },
  created() {
    this.userName = this.$globalData.userName;
  }
}
</script>
