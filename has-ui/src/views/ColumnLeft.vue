<template>
  <div class="column">
    <ProjectResponse></ProjectResponse>
    <NewParticipant></NewParticipant>
  </div>
</template>

<script>
import ProjectResponse from "@/components/ProjectResponse";
import NewParticipant from "@/components/NewParticipant";
export default {
  name: "ColumnLeft",
  components: {ProjectResponse,NewParticipant}
}
</script>

<style scoped>
.column {
  width: 30%;
  display: flex;
  justify-content: space-between;
  flex-direction: column;
}
</style>
