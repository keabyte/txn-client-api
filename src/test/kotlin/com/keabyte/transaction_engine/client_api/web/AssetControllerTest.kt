package com.keabyte.transaction_engine.client_api.web

import com.keabyte.transaction_engine.client_api.exception.BusinessException
import com.keabyte.transaction_engine.client_api.web.model.asset.CreateAssetRequest
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.validation.ConstraintViolationException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@MicronautTest
class AssetControllerTest(private val assetController: AssetController) {

    @Test
    fun `create asset`() {
        val request = CreateAssetRequest(
            "SPY",
            "SPDR S&P 500 ETF Trust",
            6
        )
        val asset = assetController.createAsset(request)

        assertThat(asset)
            .usingRecursiveComparison()
            .ignoringFields("assetCode")
            .isEqualTo(request)
    }

    @Test
    fun `get asset`() {
        val request = CreateAssetRequest(
            "SPY",
            "SPDR S&P 500 ETF Trust",
            6
        )
        assetController.createAsset(request)
        val asset = assetController.getAssetByCode("SPY")

        assertThat(asset)
            .usingRecursiveComparison()
            .isEqualTo(request)
    }

    @Test
    fun `get asset that does not exist`() {
        assertThrows<com.keabyte.transaction_engine.client_api.exception.BusinessException> {
            assetController.getAssetByCode(
                "SPY"
            )
        }
    }

    @Test
    fun `get asset with blank asset code`() {
        assertThrows<ConstraintViolationException> { assetController.getAssetByCode("") }
    }
}