<template>
  <div>
    이메일 확인 페이지
  </div>
</template>

<script>
import baseURL from '@/base-url.js'
export default {
  mounted: {
    emainConfirm: function() {
      baseURL('user/key_alter')
    }
  }
}
</script>

<style>

</style>